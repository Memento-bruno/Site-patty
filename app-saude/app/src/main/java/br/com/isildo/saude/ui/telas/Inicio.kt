package br.com.isildo.saude.ui.telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.data.Pessoa
import br.com.isildo.saude.data.Progresso
import br.com.isildo.saude.data.Remedios
import br.com.isildo.saude.ui.FormaCartao
import br.com.isildo.saude.ui.Rotas
import br.com.isildo.saude.ui.theme.Cores
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

val PtBr: Locale = Locale.forLanguageTag("pt-BR")

fun dataPorExtenso(dia: LocalDate): String =
    dia.format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", PtBr)).replaceFirstChar { it.uppercase(PtBr) }

fun hora(h: LocalTime): String = if (h.minute == 0) "%02dh".format(h.hour) else "%02dh%02d".format(h.hour, h.minute)

private data class Atalho(val titulo: String, val descricao: String, val icone: ImageVector, val cor: Color, val rota: String)

private val atalhos = listOf(
    Atalho("Remédios de hoje", "Horários e marcar o que tomou", Icons.Filled.Schedule, Cores.Remedios, Rotas.HOJE),
    Atalho("Meus remédios", "Para que serve cada um", Icons.Filled.Medication, Cores.Remedios, Rotas.REMEDIOS),
    Atalho("Alimentação", "Cardápio, prato e dicas", Icons.Filled.Restaurant, Cores.Comida, Rotas.COMIDA),
    Atalho("Exames", "Resultados e o que falta", Icons.Filled.Science, Cores.Exames, Rotas.EXAMES),
    Atalho("Médicos e consultas", "Quem cuida e o histórico", Icons.AutoMirrored.Filled.EventNote, Cores.Consultas, Rotas.CONSULTAS),
    Atalho("Minha saúde", "Condições explicadas", Icons.Filled.Favorite, Cores.Saude, Rotas.SAUDE),
    Atalho("O que falta fazer", "Próximos passos", Icons.Filled.AssignmentTurnedIn, Cores.Passos, Rotas.PASSOS),
    Atalho("Urgência e ficha médica", "Para mostrar ao médico", Icons.Filled.LocalHospital, Cores.Urgencia, Rotas.URGENCIA),
)

@Composable
fun TelaInicio(ir: (String) -> Unit) {
    val progresso = Progresso.de(LocalContext.current)
    val hoje = LocalDate.now()
    val agora = LocalTime.now()
    val saudacao = when (agora.hour) {
        in 5..11 -> "Bom dia"
        in 12..17 -> "Boa tarde"
        else -> "Boa noite"
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Cores.Fundo),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Column(Modifier.statusBarsPadding().padding(top = 20.dp, bottom = 4.dp)) {
                Text("$saudacao, ${Pessoa.APELIDO}!", style = MaterialTheme.typography.displaySmall, color = Cores.Texto)
                Text(dataPorExtenso(hoje), style = MaterialTheme.typography.titleLarge, color = Cores.TextoSuave)
            }
        }

        item { CartaoProximoRemedio(progresso, hoje, agora) { ir(Rotas.HOJE) } }

        item { CartaoAgua(progresso) }

        item {
            Text(
                "O que você quer ver?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp),
            )
        }

        items(atalhos.size) { i ->
            val a = atalhos[i]
            BotaoSecao(a.titulo, a.descricao, a.icone, a.cor) { ir(a.rota) }
        }

        item {
            Spacer(Modifier.height(4.dp))
            BotaoSecao("Ajustes", "Tamanho da letra e lembretes", Icons.Filled.Settings, Cores.TextoSuave) { ir(Rotas.AJUSTES) }
        }
        item { Spacer(Modifier.navigationBarsPadding()) }
    }
}

@Composable
private fun CartaoProximoRemedio(progresso: Progresso, hoje: LocalDate, agora: LocalTime, onVer: () -> Unit) {
    val tomadas = Remedios.tomadasDe(hoje)
    val feitas = tomadas.count { progresso.tomou(it.id, hoje) }
    val pendentes = tomadas.filter { !progresso.tomou(it.id, hoje) }
    val atrasada = pendentes.firstOrNull { it.horario <= agora }
    val proxima = atrasada ?: pendentes.firstOrNull()

    Surface(shape = FormaCartao, color = Cores.Remedios, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (proxima == null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(44.dp))
                    Spacer(Modifier.width(12.dp))
                    Text("Todos os remédios de hoje foram tomados. Muito bem!", style = MaterialTheme.typography.titleLarge, color = Color.White)
                }
            } else {
                val remedio = Remedios.porId(proxima.remedioId)
                Text(
                    if (proxima == atrasada) "Ainda não marcou como tomado" else "Próximo remédio",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.95f),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(76.dp).background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(hora(proxima.horario), style = MaterialTheme.typography.titleLarge, color = Cores.Remedios)
                    }
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(remedio.nome, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                        Text(proxima.instrucao, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                    }
                }
                Button(
                    onClick = { progresso.marcarTomada(proxima.id, true, hoje) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Cores.Remedios),
                    modifier = Modifier.fillMaxWidth().heightIn(min = 60.dp),
                ) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(28.dp))
                    Spacer(Modifier.width(10.dp))
                    Text("Já tomei este", style = MaterialTheme.typography.labelLarge)
                }
            }
            Text("Hoje: $feitas de ${tomadas.size} remédios tomados", style = MaterialTheme.typography.titleMedium, color = Color.White)
            LinearProgressIndicator(
                progress = { if (tomadas.isEmpty()) 1f else feitas / tomadas.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.3f),
                drawStopIndicator = {},
            )
            Button(
                onClick = onVer,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174A85), contentColor = Color.White),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.fillMaxWidth().heightIn(min = 60.dp),
            ) {
                Icon(Icons.Filled.Schedule, contentDescription = null, modifier = Modifier.size(26.dp))
                Spacer(Modifier.width(10.dp))
                Text("Ver todos os horários de hoje", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
private fun CartaoAgua(progresso: Progresso) {
    val copos = progresso.copos()
    val meta = 8
    Surface(
        shape = FormaCartao,
        color = Cores.Cartao,
        border = BorderStroke(1.5.dp, Cores.Borda),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.WaterDrop, contentDescription = null, tint = Cores.Azul, modifier = Modifier.size(34.dp))
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text("Água de hoje", style = MaterialTheme.typography.titleLarge)
                    Text("Meta: 8 copos (cerca de 2 litros)", style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                FilledIconButton(
                    onClick = { progresso.mudarCopos(-1) },
                    modifier = Modifier.size(60.dp).semantics { contentDescription = "Tirar um copo" },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Cores.AzulClaro, contentColor = Cores.Azul),
                ) { Icon(Icons.Filled.Remove, contentDescription = null, modifier = Modifier.size(32.dp)) }
                Text(
                    "$copos de $meta copos",
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (copos >= meta) Cores.Ok else Cores.Texto,
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )
                FilledIconButton(
                    onClick = { progresso.mudarCopos(1) },
                    modifier = Modifier.size(60.dp).semantics { contentDescription = "Bebi mais um copo" },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Cores.Azul, contentColor = Color.White),
                ) { Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(32.dp)) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                repeat(meta) { i ->
                    Box(
                        Modifier
                            .weight(1f)
                            .height(14.dp)
                            .background(if (i < copos) Cores.Azul else Cores.AzulClaro, RoundedCornerShape(7.dp)),
                    )
                }
            }
        }
    }
}

@Composable
fun BotaoSecao(titulo: String, descricao: String, icone: ImageVector, cor: Color, onClick: () -> Unit) {
    Surface(
        shape = FormaCartao,
        color = Cores.Cartao,
        border = BorderStroke(2.dp, cor.copy(alpha = 0.55f)),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 92.dp)
            .clip(FormaCartao)
            .clickable(role = Role.Button, onClick = onClick),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(62.dp).background(cor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icone, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(titulo, style = MaterialTheme.typography.titleLarge, color = Cores.Texto)
                Text(descricao, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
            }
            Text("›", style = MaterialTheme.typography.displaySmall, color = cor)
        }
    }
}
