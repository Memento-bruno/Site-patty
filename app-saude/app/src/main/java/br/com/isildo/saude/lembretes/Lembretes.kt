package br.com.isildo.saude.lembretes

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import br.com.isildo.saude.MainActivity
import br.com.isildo.saude.R
import br.com.isildo.saude.data.Progresso
import br.com.isildo.saude.data.Remedios
import br.com.isildo.saude.data.Tomada
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Lembretes de remédio: um alarme por horário, que avisa com uma notificação.
 * Depois de tocar, o alarme se agenda de novo para o dia seguinte.
 */
object Lembretes {

    const val CANAL = "remedios"
    private const val EXTRA_TOMADA = "tomada"
    private const val EXTRA_DIA = "dia"
    const val ACAO_TOCAR = "br.com.isildo.saude.TOCAR"
    const val ACAO_TOMEI = "br.com.isildo.saude.TOMEI"

    /** Janela de tolerância do alarme; poucos minutos não fazem diferença aqui. */
    private const val JANELA_MS = 5 * 60 * 1000L

    fun criarCanal(context: Context) {
        val canal = NotificationChannel(CANAL, "Hora do remédio", NotificationManager.IMPORTANCE_HIGH).apply {
            description = "Avisa na hora de tomar cada remédio"
            enableVibration(true)
        }
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(canal)
    }

    fun agendarTodos(context: Context) {
        val ligado = Progresso.de(context).lembretesLigados
        Remedios.tomadas.forEach { tomada ->
            if (ligado) agendar(context, tomada) else cancelar(context, tomada)
        }
    }

    private fun proximoDisparo(tomada: Tomada, agora: LocalDateTime = LocalDateTime.now()): LocalDateTime? {
        var dia = agora.toLocalDate()
        if (!agora.toLocalTime().isBefore(tomada.horario)) dia = dia.plusDays(1)
        return if (tomada.valeEm(dia)) dia.atTime(tomada.horario) else null
    }

    fun agendar(context: Context, tomada: Tomada) {
        val quando = proximoDisparo(tomada) ?: run { cancelar(context, tomada); return }
        val ms = quando.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val alarme = context.getSystemService(AlarmManager::class.java)
        alarme.setWindow(AlarmManager.RTC_WAKEUP, ms, JANELA_MS, intentAlarme(context, tomada))
    }

    fun cancelar(context: Context, tomada: Tomada) {
        context.getSystemService(AlarmManager::class.java).cancel(intentAlarme(context, tomada))
    }

    private fun intentAlarme(context: Context, tomada: Tomada): PendingIntent {
        val intent = Intent(context, LembreteReceiver::class.java)
            .setAction(ACAO_TOCAR)
            .putExtra(EXTRA_TOMADA, tomada.id)
        return PendingIntent.getBroadcast(
            context, tomada.id.hashCode(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    fun notificar(context: Context, tomada: Tomada) {
        val progresso = Progresso.de(context)
        val hoje = LocalDate.now()
        if (progresso.tomou(tomada.id, hoje)) return
        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) return

        val remedio = Remedios.porId(tomada.remedioId)
        val abrir = PendingIntent.getActivity(
            context, 0,
            Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val tomei = PendingIntent.getBroadcast(
            context, tomada.id.hashCode() + 1,
            Intent(context, LembreteReceiver::class.java)
                .setAction(ACAO_TOMEI)
                .putExtra(EXTRA_TOMADA, tomada.id)
                .putExtra(EXTRA_DIA, hoje.toString()),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val texto = "${tomada.instrucao}."
        val notificacao = NotificationCompat.Builder(context, CANAL)
            .setSmallIcon(R.drawable.ic_notificacao)
            .setContentTitle("Hora do remédio: ${remedio.nome}")
            .setContentText(texto)
            .setStyle(NotificationCompat.BigTextStyle().bigText(texto))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setContentIntent(abrir)
            .setAutoCancel(true)
            .addAction(0, "JÁ TOMEI", tomei)
            .build()
        NotificationManagerCompat.from(context).notify(tomada.id.hashCode(), notificacao)
    }

    internal fun aoReceber(context: Context, intent: Intent) {
        val tomada = Remedios.tomadas.firstOrNull { it.id == intent.getStringExtra(EXTRA_TOMADA) } ?: return
        when (intent.action) {
            ACAO_TOCAR -> {
                notificar(context, tomada)
                agendar(context, tomada)
            }
            ACAO_TOMEI -> {
                val dia = intent.getStringExtra(EXTRA_DIA)?.let(LocalDate::parse) ?: LocalDate.now()
                Progresso.de(context).marcarTomada(tomada.id, true, dia)
                NotificationManagerCompat.from(context).cancel(tomada.id.hashCode())
            }
        }
    }
}

class LembreteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) = Lembretes.aoReceber(context, intent)
}

/** Reagenda os lembretes quando o celular liga, o app é atualizado ou o relógio muda. */
class InicioCelularReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Lembretes.criarCanal(context)
        Lembretes.agendarTodos(context)
    }
}
