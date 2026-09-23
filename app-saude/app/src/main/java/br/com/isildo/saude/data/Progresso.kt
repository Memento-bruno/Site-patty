package br.com.isildo.saude.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

/**
 * Guarda no celular o que foi marcado: remédios tomados no dia, copos de água,
 * próximos passos concluídos e as preferências (tamanho da letra, lembretes).
 * Tudo fica só no aparelho.
 */
class Progresso private constructor(context: Context) {

    private val prefs = context.getSharedPreferences("progresso", Context.MODE_PRIVATE)

    /** Muda a cada gravação, para a tela se atualizar. */
    var versao by mutableIntStateOf(0)
        private set

    var escalaFonte by mutableStateOf(prefs.getFloat(ESCALA, 1.15f))
        private set

    var lembretesLigados by mutableStateOf(prefs.getBoolean(LEMBRETES, true))
        private set

    fun tomou(tomadaId: String, dia: LocalDate = LocalDate.now()): Boolean {
        versao
        return prefs.getBoolean(chaveTomada(tomadaId, dia), false)
    }

    fun marcarTomada(tomadaId: String, tomou: Boolean, dia: LocalDate = LocalDate.now()) {
        prefs.edit().putBoolean(chaveTomada(tomadaId, dia), tomou).apply()
        versao++
    }

    fun copos(dia: LocalDate = LocalDate.now()): Int {
        versao
        return prefs.getInt("agua_$dia", 0)
    }

    fun mudarCopos(delta: Int, dia: LocalDate = LocalDate.now()) {
        val novo = (copos(dia) + delta).coerceIn(0, 20)
        prefs.edit().putInt("agua_$dia", novo).apply()
        versao++
    }

    fun passoFeito(id: String): Boolean {
        versao
        return prefs.getBoolean("passo_$id", false)
    }

    fun marcarPasso(id: String, feito: Boolean) {
        prefs.edit().putBoolean("passo_$id", feito).apply()
        versao++
    }

    fun mudarEscala(nova: Float) {
        escalaFonte = nova
        prefs.edit().putFloat(ESCALA, nova).apply()
    }

    fun mudarLembretes(ligado: Boolean) {
        lembretesLigados = ligado
        prefs.edit().putBoolean(LEMBRETES, ligado).apply()
    }

    /** Recarrega depois de uma marcação feita pela notificação. */
    fun recarregar() {
        versao++
    }

    companion object {
        private const val ESCALA = "escala_fonte"
        private const val LEMBRETES = "lembretes_ligados"

        fun chaveTomada(tomadaId: String, dia: LocalDate) = "tomada_${dia}_$tomadaId"

        @Volatile
        private var instancia: Progresso? = null

        fun de(context: Context): Progresso =
            instancia ?: synchronized(this) {
                instancia ?: Progresso(context.applicationContext).also { instancia = it }
            }
    }
}
