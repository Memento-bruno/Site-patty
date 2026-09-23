package br.com.isildo.saude.ui.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.data.Fontes
import br.com.isildo.saude.data.Periodo
import br.com.isildo.saude.data.Progresso
import br.com.isildo.saude.data.Remedios
import br.com.isildo.saude.data.Rotina
import br.com.isildo.saude.ui.CaixaAviso
import br.com.isildo.saude.ui.Cartao
import br.com.isildo.saude.ui.CartaoExpansivel
import br.com.isildo.saude.ui.Etiqueta
import br.com.isildo.saude.ui.LinhaInfo
import br.com.isildo.saude.ui.LinhaMarcar
import br.com.isildo.saude.ui.Rodape
import br.com.isildo.saude.ui.TelaBase
import br.com.isildo.saude.ui.TituloSecao
import br.com.isildo.saude.ui.theme.Cores
import java.time.LocalDate

fun corDoPeriodo(p: Periodo): Pair<Color, Color> = when (p) {
    Periodo.MANHA -> Cores.Manha to Cores.ManhaClaro
    Periodo.TARDE -> Cores.Tarde to Cores.TardeClaro
    Periodo.NOITE -> Cores.Noite to Cores.NoiteClaro
}

fun iconeDoPeriodo(p: Periodo): ImageVector = when (p) {
    Periodo.MANHA -> Icons.Filled.WbSunny
    Periodo.TARDE -> Icons.Filled.WbTwilight
    Periodo.NOITE -> Icons.Filled.Bedtime
}

@Composable
fun SeloHorario(texto: String, cor: Color, fundo: Color) {
    Box(
        Modifier
            .width(84.dp)
            .background(fundo, RoundedCornerShape(14.dp))
            .padding(vertical = 10.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(texto, style = MaterialTheme.typography.titleMedium, color = cor, textAlign = TextAlign.Center)
    }
}

/** Remédios de hoje, na ordem do dia, junto com as refeições. */
@Composable
fun TelaHoje(onVoltar: () -> Unit) {
    val progresso = Progresso.de(LocalContext.current)
    val hoje = LocalDate.now()
    val tomadasHoje = Remedios.tomadasDe(hoje).associateBy { it.id }
    val feitas = tomadasHoje.keys.count { progresso.tomou(it, hoje) }

    TelaBase(
        titulo = "Remédios de hoje",
        subtitulo = dataPorExtenso(hoje),
        cor = Cores.Remedios,
        icone = Icons.Filled.Schedule,
        onVoltar = onVoltar,
    ) {
        item {
            Cartao(fundo = Cores.RemediosClaro, borda = Cores.Remedios) {
                Text("$feitas de ${tomadasHoje.size} tomados", style = MaterialTheme.typography.headlineSmall, color = Cores.Remedios)
                Text(
                    "Toque no quadrado de cada remédio depois de tomar. Amanhã a lista começa de novo.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        var periodoAtual: Periodo? = null
        Rotina.itens.forEach { linha ->
            val tomadas = linha.tomadaIds.mapNotNull { tomadasHoje[it] }
            if (linha.tomadaIds.isNotEmpty() && tomadas.isEmpty()) return@forEach // tratamento já terminou

            val periodo = tomadas.firstOrNull()?.periodo ?: periodoDoTexto(linha.horario)
            if (periodo != periodoAtual) {
                periodoAtual = periodo
                item(key = "p_$periodo") {
                    val (cor, _) = corDoPeriodo(periodo)
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 10.dp)) {
                        Icon(iconeDoPeriodo(periodo), contentDescription = null, tint = cor, modifier = Modifier.size(34.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(periodo.nome, style = MaterialTheme.typography.headlineSmall, color = cor)
                    }
                }
            }

            if (linha.refeicao) {
                item(key = linha.titulo) {
                    val (cor, fundo) = corDoPeriodo(periodo)
                    Cartao {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            SeloHorario(linha.horario, cor, fundo)
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Filled.Restaurant, contentDescription = null, tint = Cores.Comida, modifier = Modifier.size(24.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text(linha.titulo.substringBefore(" +"), style = MaterialTheme.typography.titleMedium)
                                }
                                Text(linha.detalhe, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
                            }
                        }
                    }
                }
            }
            run {
                tomadas.forEach { tomada ->
                    item(key = tomada.id) {
                        val remedio = Remedios.porId(tomada.remedioId)
                        val (cor, fundo) = corDoPeriodo(tomada.periodo)
                        LinhaMarcar(
                            titulo = remedio.nome,
                            detalhe = tomada.instrucao + ".",
                            marcado = progresso.tomou(tomada.id, hoje),
                            cor = cor,
                            textoMarcado = "Tomei",
                            textoDesmarcado = "Tomar",
                            onMudar = { progresso.marcarTomada(tomada.id, it, hoje) },
                            esquerda = { SeloHorario(hora(tomada.horario), cor, fundo) },
                        )
                    }
                }
            }
        }

        item {
            CaixaAviso(
                "Se esquecer um remédio",
                "Não tome dois de uma vez sem orientação. Em caso de dúvida, pergunte à médica ou ao farmacêutico.",
                Cores.Atencao, Cores.AtencaoClaro, Icons.Filled.Info,
            )
        }
        item { Rodape("Horários organizados pelo ${Fontes.PLANO}. Se houver diferença, vale a receita médica.") }
    }
}

private fun periodoDoTexto(horario: String): Periodo {
    val h = horario.takeWhile { it.isDigit() }.toIntOrNull() ?: 21
    return when {
        h < 12 -> Periodo.MANHA
        h < 18 -> Periodo.TARDE
        else -> Periodo.NOITE
    }
}

/** Ficha de cada remédio: para que serve e como tomar. */
@Composable
fun TelaRemedios(onVoltar: () -> Unit) {
    val hoje = LocalDate.now()
    TelaBase(
        titulo = "Meus remédios",
        subtitulo = "${Remedios.atuais.size} remédios em uso",
        cor = Cores.Remedios,
        icone = Icons.Filled.Medication,
        onVoltar = onVoltar,
    ) {
        item {
            Text(
                "Toque em um remédio para ver para que serve e quem receitou.",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Remedios.atuais.forEach { r ->
            val horarios = Remedios.tomadas.filter { it.remedioId == r.id }
            val terminou = horarios.none { it.valeEm(hoje) }
            item(key = r.id) {
                CartaoExpansivel(
                    titulo = r.nome,
                    subtitulo = r.nomeComercial,
                    cor = Cores.Remedios,
                    topo = {
                        horarios.forEach { t ->
                            val (cor, fundo) = corDoPeriodo(t.periodo)
                            Etiqueta(hora(t.horario), cor, fundo)
                            Spacer(Modifier.width(8.dp))
                        }
                        if (r.doseAConfirmar) Etiqueta("Dose a confirmar", Cores.Alerta, Cores.AlertaClaro)
                        if (terminou) Etiqueta("90 dias concluídos", Cores.Atencao, Cores.AtencaoClaro)
                    },
                ) {
                    Cartao(fundo = Cores.RemediosClaro, borda = Cores.Remedios) {
                        Text("Como tomar", style = MaterialTheme.typography.labelMedium, color = Cores.Remedios)
                        Text(r.comoTomar, style = MaterialTheme.typography.titleMedium)
                    }
                    LinhaInfo("Para que serve", r.paraQueServe)
                    LinhaInfo("Dose", r.dose)
                    LinhaInfo("Quem receitou", r.quemReceitou)
                    LinhaInfo("Começou em", r.inicio)
                    LinhaInfo("Por quanto tempo", r.duracao)
                    if (r.observacao != null) {
                        CaixaAviso("Atenção", r.observacao, Cores.Atencao, Cores.AtencaoClaro, Icons.Filled.Warning)
                    }
                }
            }
        }

        item { TituloSecao("Remédios que NÃO está tomando agora") }
        item {
            Text(
                "Usados antes ou só uma vez. Estão aqui para consulta.",
                style = MaterialTheme.typography.bodyLarge,
                color = Cores.TextoSuave,
            )
        }
        Remedios.naoToma.forEach { r ->
            item(key = r.nome) {
                Cartao(fundo = Color(0xFFF2F0EC)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Block, contentDescription = null, tint = Cores.TextoSuave, modifier = Modifier.size(28.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(r.nome, style = MaterialTheme.typography.titleMedium)
                    }
                    Text(r.detalhe, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        item {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CaixaAviso(
                    "Remédio e comida trabalham juntos",
                    "A alimentação ajuda a controlar açúcar, pressão e colesterol. Os remédios agem em pontos específicos do corpo. Um não substitui o outro.",
                    Cores.Comida, Cores.ComidaClaro, Icons.Filled.Restaurant,
                )
                Rodape("Fontes: ${Fontes.HISTORICO} e ${Fontes.PLANO}.")
            }
        }
    }
}
