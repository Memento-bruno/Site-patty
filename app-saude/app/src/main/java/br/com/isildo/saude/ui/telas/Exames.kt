package br.com.isildo.saude.ui.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.data.Exames
import br.com.isildo.saude.data.Fontes
import br.com.isildo.saude.data.ResultadoExame
import br.com.isildo.saude.data.Situacao
import br.com.isildo.saude.ui.CaixaAviso
import br.com.isildo.saude.ui.Cartao
import br.com.isildo.saude.ui.CartaoExpansivel
import br.com.isildo.saude.ui.Etiqueta
import br.com.isildo.saude.ui.LinhaInfo
import br.com.isildo.saude.ui.Rodape
import br.com.isildo.saude.ui.TelaBase
import br.com.isildo.saude.ui.TituloSecao
import br.com.isildo.saude.ui.theme.Cores

private fun etiquetaDe(s: Situacao): Triple<String, Color, Color>? = when (s) {
    Situacao.ALTO -> Triple("Alto", Cores.Alerta, Cores.AlertaClaro)
    Situacao.BAIXO -> Triple("Baixo", Cores.Alerta, Cores.AlertaClaro)
    Situacao.ATENCAO -> Triple("Atenção", Cores.Atencao, Cores.AtencaoClaro)
    Situacao.NORMAL -> Triple("Normal", Cores.Ok, Cores.OkClaro)
    Situacao.SEM_AVALIACAO -> null
}

@Composable
fun TelaExames(onVoltar: () -> Unit) {
    TelaBase(
        titulo = "Exames",
        subtitulo = "O que falta fazer e os resultados",
        cor = Cores.Exames,
        icone = Icons.Filled.Science,
        onVoltar = onVoltar,
    ) {
        item { TituloSecao("Falta fazer", Cores.Exames) }
        Exames.pendentes.forEach { p ->
            item(key = p.nome) {
                Cartao(fundo = Cores.ExamesClaro, borda = Cores.Exames) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.HourglassTop, contentDescription = null, tint = Cores.Exames, modifier = Modifier.size(30.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(p.nome, style = MaterialTheme.typography.titleLarge)
                    }
                    LinhaInfo("Quem pediu", p.pedidoPor)
                    Text(p.detalhe, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        item { TituloSecao("Últimos exames de sangue e urina", Cores.Exames) }
        item {
            Text(
                "${Exames.DATA_ULTIMOS}, ${Exames.LOCAL_ULTIMOS}. Toque em cada grupo para ver os valores.",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Exames.ultimos.forEachIndexed { i, grupo ->
            val alterados = grupo.resultados.count { it.situacao in listOf(Situacao.ALTO, Situacao.BAIXO) }
            item(key = grupo.titulo) {
                CartaoExpansivel(
                    titulo = grupo.titulo,
                    cor = Cores.Exames,
                    subtitulo = if (alterados > 0) "$alterados resultado(s) fora do normal" else null,
                    abertoInicial = i == 0,
                ) {
                    grupo.resultados.forEachIndexed { j, r ->
                        if (j > 0) HorizontalDivider(color = Cores.Borda)
                        LinhaResultado(r)
                    }
                }
            }
        }
        item {
            CaixaAviso(
                "Sobre as etiquetas",
                "\"Alto\", \"Baixo\" e \"Normal\" só aparecem quando os documentos dizem isso. Os outros valores devem ser avaliados pela médica.",
                Cores.Exames, Cores.ExamesClaro, Icons.Filled.Info,
            )
        }

        item { TituloSecao("Exames anteriores", Cores.Exames) }
        Exames.anteriores.forEach { e ->
            item(key = e.nome) {
                Cartao {
                    Text(e.data, style = MaterialTheme.typography.labelMedium, color = Cores.Exames)
                    Text(e.nome, style = MaterialTheme.typography.titleMedium)
                    Text(e.resultado, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        item { Rodape("Fonte: ${Fontes.HISTORICO}.") }
    }
}

@Composable
private fun LinhaResultado(r: ResultadoExame) {
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(r.nome, style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(r.valor, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.weight(1f, fill = false))
            etiquetaDe(r.situacao)?.let { (t, c, f) ->
                Spacer(Modifier.width(12.dp))
                Etiqueta(t, c, f)
            }
        }
        if (r.explicacao.isNotBlank()) {
            Text(r.explicacao, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
        }
    }
}
