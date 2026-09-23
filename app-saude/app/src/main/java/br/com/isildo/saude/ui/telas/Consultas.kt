package br.com.isildo.saude.ui.telas

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.data.Consultas
import br.com.isildo.saude.data.Fontes
import br.com.isildo.saude.ui.Cartao
import br.com.isildo.saude.ui.CartaoExpansivel
import br.com.isildo.saude.ui.Etiqueta
import br.com.isildo.saude.ui.Marcador
import br.com.isildo.saude.ui.Rodape
import br.com.isildo.saude.ui.TelaBase
import br.com.isildo.saude.ui.TituloSecao
import br.com.isildo.saude.ui.theme.Cores

@Composable
fun TelaConsultas(onVoltar: () -> Unit) {
    TelaBase(
        titulo = "Médicos e consultas",
        subtitulo = "Quem cuida da sua saúde",
        cor = Cores.Consultas,
        icone = Icons.AutoMirrored.Filled.EventNote,
        onVoltar = onVoltar,
    ) {
        item { TituloSecao("Meus médicos", Cores.Consultas) }
        Consultas.equipeAtual.forEach { m ->
            item(key = m.nome) {
                Cartao {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Person, contentDescription = null, tint = Cores.Consultas, modifier = Modifier.size(32.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(m.nome, style = MaterialTheme.typography.titleLarge)
                    }
                    Etiqueta(m.especialidade, Cores.Consultas, Cores.ConsultasClaro)
                    Text(m.local, style = MaterialTheme.typography.bodyLarge, color = Cores.TextoSuave)
                    Text(m.papel, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        item { TituloSecao("Histórico, do mais novo ao mais antigo", Cores.Consultas) }
        Consultas.historico.forEachIndexed { i, c ->
            item(key = c.data + c.especialidade) {
                CartaoExpansivel(
                    titulo = "${c.data} · ${c.especialidade}",
                    subtitulo = "${c.local}\n${c.profissional}",
                    cor = Cores.Consultas,
                    abertoInicial = i == 0,
                ) {
                    Bloco("Por que foi", c.motivo)
                    Bloco("O que foi decidido", c.decidido)
                    Bloco("Exames e resultados", c.resultados)
                }
            }
        }
        item { Rodape("Fonte: ${Fontes.HISTORICO}.") }
    }
}

@Composable
private fun Bloco(titulo: String, itens: List<String>) {
    if (itens.isEmpty()) return
    Text(titulo, style = MaterialTheme.typography.titleMedium, color = Cores.Consultas)
    itens.forEach { Marcador(it, Cores.Consultas) }
}
