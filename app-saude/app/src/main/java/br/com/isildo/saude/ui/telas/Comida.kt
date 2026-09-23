package br.com.isildo.saude.ui.telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.data.Alimentacao
import br.com.isildo.saude.data.DiaCardapio
import br.com.isildo.saude.data.Fontes
import br.com.isildo.saude.ui.CaixaAviso
import br.com.isildo.saude.ui.Cartao
import br.com.isildo.saude.ui.CartaoExpansivel
import br.com.isildo.saude.ui.LinhaInfo
import br.com.isildo.saude.ui.ListaNumerada
import br.com.isildo.saude.ui.Marcador
import br.com.isildo.saude.ui.Rodape
import br.com.isildo.saude.ui.TelaBase
import br.com.isildo.saude.ui.TituloSecao
import br.com.isildo.saude.ui.theme.Cores
import java.time.DayOfWeek
import java.time.LocalDate

enum class SecaoComida(val titulo: String, val descricao: String, val icone: ImageVector) {
    CARDAPIO("Cardápio da semana", "O que comer em cada dia", Icons.Filled.CalendarMonth),
    PRATO("Como montar o prato", "Almoço e jantar, e o tamanho das porções", Icons.Filled.PieChart),
    ESCOLHAS("Pode, com moderação, evite", "O que comer sempre e o que deixar de lado", Icons.Filled.CheckCircle),
    IDEIAS("Ideias de refeições", "Café, lanches, almoço e jantar", Icons.Filled.RestaurantMenu),
    TROCAS("Trocas", "Se faltar um alimento, troque por outro", Icons.Filled.SwapHoriz),
    DOCES("Doces e chocolate", "Quanto e quantas vezes por semana", Icons.Filled.Cookie),
    MERCADO("No mercado", "O que comprar e como ler o rótulo", Icons.Filled.ShoppingCart),
    METAS("Metas do dia", "Água, proteína, fibras e sal", Icons.Filled.Flag),
    CUIDADOS("Cuidados importantes", "Sal light, jejum, frutas e mais", Icons.Filled.Warning),
}

@Composable
fun TelaComida(onVoltar: () -> Unit, abrir: (SecaoComida) -> Unit) {
    val hoje = Alimentacao.cardapioDe(LocalDate.now().dayOfWeek)
    TelaBase(
        titulo = "Alimentação",
        subtitulo = "Plano alimentar de 22/09/2026",
        cor = Cores.Comida,
        icone = Icons.Filled.Restaurant,
        onVoltar = onVoltar,
    ) {
        item {
            Cartao(fundo = Cores.ComidaClaro, borda = Cores.Comida) {
                Text("Cardápio de hoje, ${hoje.nome.lowercase()}", style = MaterialTheme.typography.titleLarge, color = Cores.Comida)
                RefeicoesDoDia(hoje)
            }
        }
        item { TituloSecao("Escolha um assunto") }
        SecaoComida.entries.forEach { s ->
            item(key = s.name) { BotaoSecao(s.titulo, s.descricao, s.icone, Cores.Comida) { abrir(s) } }
        }
        item {
            CaixaAviso(
                "O foco deste período",
                "Comida de verdade, horários regulares, menos sal e ultraprocessados, porções equilibradas e nada de dietas radicais.",
                Cores.Comida, Cores.ComidaClaro, Icons.Filled.Lightbulb,
            )
        }
        item {
            Rodape(
                "Este guia não substitui a consulta com nutricionista e não muda nenhuma receita médica. " +
                    "Fonte: ${Fontes.PLANO}.",
            )
        }
    }
}

@Composable
private fun RefeicoesDoDia(dia: DiaCardapio) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        LinhaInfo("Café da manhã (08h20)", dia.cafe)
        LinhaInfo("Almoço (12h às 13h)", dia.almoco)
        LinhaInfo("Lanche, se tiver fome (15h30 às 17h30)", dia.lanche)
        LinhaInfo("Jantar (19h às 20h30)", dia.jantar)
    }
}

@Composable
fun TelaComidaSecao(secao: SecaoComida, onVoltar: () -> Unit) {
    TelaBase(
        titulo = secao.titulo,
        cor = Cores.Comida,
        icone = secao.icone,
        onVoltar = onVoltar,
        textoVoltar = "Voltar para Alimentação",
    ) {
        when (secao) {
            SecaoComida.CARDAPIO -> item { Cardapio() }
            SecaoComida.PRATO -> {
                item { Prato() }
                item { TituloSecao("Tamanho das porções") }
                Alimentacao.porcoes.forEach { p ->
                    item(key = p.alimento) {
                        Cartao {
                            Text(p.alimento, style = MaterialTheme.typography.titleMedium, color = Cores.Comida)
                            Text(p.quanto, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
                item { Rodape("As porções são uma referência inicial. A nutricionista poderá ajustar.") }
            }
            SecaoComida.ESCOLHAS -> {
                item { ListaEscolhas("Coma sempre", Alimentacao.priorize, Cores.Ok, Cores.OkClaro, Icons.Filled.CheckCircle) }
                item { ListaEscolhas("Pode, mas com porção pequena", Alimentacao.comPorcao, Cores.Atencao, Cores.AtencaoClaro, Icons.Filled.Info) }
                item { ListaEscolhas("Evite no dia a dia", Alimentacao.evite, Cores.Alerta, Cores.AlertaClaro, Icons.Filled.Block) }
                item {
                    Rodape("A ideia não é proibir. É separar o que pode aparecer sempre, o que pede cuidado com a porção e o que fica para ocasiões especiais.")
                }
            }
            SecaoComida.IDEIAS -> {
                item {
                    CartaoExpansivel("Café da manhã", Cores.Comida, "${Alimentacao.cafeDaManha.size} ideias", abertoInicial = true) {
                        ListaNumerada(Alimentacao.cafeDaManha, Cores.Comida)
                    }
                }
                item {
                    CartaoExpansivel("Lanches", Cores.Comida, "${Alimentacao.lanches.size} ideias") {
                        ListaNumerada(Alimentacao.lanches, Cores.Comida)
                    }
                }
                item {
                    CartaoExpansivel("Almoço e jantar", Cores.Comida, "${Alimentacao.almocoJantar.size} ideias") {
                        ListaNumerada(Alimentacao.almocoJantar, Cores.Comida)
                    }
                }
            }
            SecaoComida.TROCAS -> {
                item { Text("Trocar um alimento por outro mantém a refeição equilibrada, sem regra rígida.", style = MaterialTheme.typography.bodyLarge) }
                Alimentacao.trocas.forEach { t ->
                    item(key = t.seTem) {
                        Cartao {
                            LinhaInfo("Se no prato tem", t.seTem)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.SwapHoriz, contentDescription = null, tint = Cores.Comida, modifier = Modifier.size(30.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Pode trocar por", style = MaterialTheme.typography.labelMedium, color = Cores.Comida)
                            }
                            Text(t.trocaPor, style = MaterialTheme.typography.titleMedium)
                            Text("Dica: ${t.dica}", style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
                        }
                    }
                }
            }
            SecaoComida.DOCES -> {
                item {
                    Text(
                        "Doce não precisa ser proibido. O segredo é planejar: porção certa e poucas vezes na semana.",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Alimentacao.doces.forEach { d ->
                    item(key = d.tipo) {
                        Cartao {
                            Text(d.tipo, style = MaterialTheme.typography.titleLarge)
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Column(Modifier.weight(1f)) { LinhaInfo("Quanto", d.porcao) }
                                Column(Modifier.weight(1f)) { LinhaInfo("Quantas vezes", d.frequencia) }
                            }
                            Text(d.oQueE, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
                        }
                    }
                }
                item { CaixaAviso("Melhor momento", Alimentacao.DOCE_MELHOR_MOMENTO, Cores.Comida, Cores.ComidaClaro, Icons.Filled.Lightbulb) }
                item {
                    CaixaAviso(
                        "Light, diet e zero",
                        "Não quer dizer que pode à vontade. Evite juntar chocolate + refrigerante + suco + outra sobremesa.",
                        Cores.Atencao, Cores.AtencaoClaro, Icons.Filled.Warning,
                    )
                }
            }
            SecaoComida.MERCADO -> {
                item { CaixaAviso("Regra mais importante", Alimentacao.REGRA_COMPRAS, Cores.Comida, Cores.ComidaClaro, Icons.Filled.Lightbulb) }
                Alimentacao.compras.forEach { c ->
                    item(key = c.categoria) {
                        Cartao {
                            Text(c.categoria, style = MaterialTheme.typography.titleLarge)
                            LinhaComIcone(Icons.Filled.CheckCircle, Cores.Ok, "Procure", c.procure)
                            LinhaComIcone(Icons.Filled.Block, Cores.Alerta, "Evite", c.evite)
                        }
                    }
                }
                item { TituloSecao("Como ler o rótulo em 5 passos") }
                item { Cartao { ListaNumerada(Alimentacao.rotulo, Cores.Comida) } }
                item { TituloSecao("Como escolher o iogurte") }
                item { Cartao { Alimentacao.iogurte.forEach { Marcador(it, Cores.Comida) } } }
                item { Rodape("Marcas e embalagens mudam. O rótulo do produto na loja é sempre a referência final.") }
            }
            SecaoComida.METAS -> {
                Alimentacao.metas.forEach { m ->
                    item(key = m.nome) {
                        Cartao {
                            Text(m.nome, style = MaterialTheme.typography.labelMedium, color = Cores.Comida)
                            Text(m.valor, style = MaterialTheme.typography.titleLarge)
                            Text(m.comoFazer, style = MaterialTheme.typography.bodyLarge, color = Cores.TextoSuave)
                        }
                    }
                }
                item { TituloSecao("O que a nutricionista ainda vai definir") }
                item { Cartao { Alimentacao.nutricionistaVaiDefinir.forEach { Marcador(it, Cores.Comida) } } }
            }
            SecaoComida.CUIDADOS -> {
                Alimentacao.avisos.forEachIndexed { i, a ->
                    item(key = a.titulo) {
                        val serio = i < 2
                        CaixaAviso(
                            a.titulo, a.texto,
                            if (serio) Cores.Alerta else Cores.Comida,
                            if (serio) Cores.AlertaClaro else Cores.ComidaClaro,
                            if (serio) Icons.Filled.Warning else Icons.Filled.Lightbulb,
                        )
                    }
                }
                item { CaixaAviso("Em refeição de família", Alimentacao.REFEICAO_FAMILIA, Cores.Comida, Cores.ComidaClaro, Icons.Filled.Restaurant) }
            }
        }
    }
}

@Composable
private fun Cardapio() {
    var dia by rememberSaveable { mutableStateOf(LocalDate.now().dayOfWeek) }
    val nomesCurtos = mapOf(
        DayOfWeek.MONDAY to "Seg", DayOfWeek.TUESDAY to "Ter", DayOfWeek.WEDNESDAY to "Qua",
        DayOfWeek.THURSDAY to "Qui", DayOfWeek.FRIDAY to "Sex", DayOfWeek.SATURDAY to "Sáb", DayOfWeek.SUNDAY to "Dom",
    )
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Escolha o dia:", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Alimentacao.cardapio.forEach { c ->
                val hoje = c.dia == LocalDate.now().dayOfWeek
                FilterChip(
                    selected = c.dia == dia,
                    onClick = { dia = c.dia },
                    label = {
                        Text(
                            if (hoje) "${nomesCurtos[c.dia]} (hoje)" else nomesCurtos[c.dia]!!,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(vertical = 10.dp),
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Cores.Comida,
                        selectedLabelColor = Color.White,
                    ),
                    modifier = Modifier.heightIn(min = 52.dp).semantics { contentDescription = c.nome },
                )
            }
        }
        val c = Alimentacao.cardapioDe(dia)
        Cartao(fundo = Cores.ComidaClaro, borda = Cores.Comida) {
            Text(c.nome, style = MaterialTheme.typography.headlineSmall, color = Cores.Comida)
            RefeicoesDoDia(c)
        }
        CaixaAviso("Sem perfeccionismo", Alimentacao.SEM_PERFECCIONISMO, Cores.Comida, Cores.ComidaClaro, Icons.Filled.Lightbulb)
        if (dia == DayOfWeek.SUNDAY || dia == DayOfWeek.SATURDAY) {
            CaixaAviso("Em refeição de família", Alimentacao.REFEICAO_FAMILIA, Cores.Comida, Cores.ComidaClaro, Icons.Filled.Restaurant)
        }
    }
}

@Composable
private fun Prato() {
    val verde = Color(0xFF43A047)
    val vermelho = Color(0xFFC62828)
    val amarelo = Color(0xFFF9A825)
    Cartao {
        Text("Almoço e jantar", style = MaterialTheme.typography.titleLarge)
        Box(Modifier.fillMaxWidth().padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
            Canvas(
                Modifier
                    .size(220.dp)
                    .semantics { contentDescription = "Prato: metade verduras, um quarto proteína, um quarto carboidrato" },
            ) {
                drawArc(verde, startAngle = 90f, sweepAngle = 180f, useCenter = true)
                drawArc(vermelho, startAngle = 270f, sweepAngle = 90f, useCenter = true)
                drawArc(amarelo, startAngle = 0f, sweepAngle = 90f, useCenter = true)
                drawCircle(Color.White, style = Stroke(width = 10f))
                drawCircle(Color(0xFF9E9E9E), radius = size.minDimension / 2 + 8f, style = Stroke(width = 6f))
            }
        }
        Legenda(verde, Alimentacao.PRATO_VERDURAS)
        Legenda(vermelho, Alimentacao.PRATO_PROTEINA)
        Legenda(amarelo, Alimentacao.PRATO_CARBO)
        Legenda(Color(0xFF6D4C41), Alimentacao.PRATO_FEIJAO)
        Text(Alimentacao.PRATO_OBS, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
    }
}

@Composable
private fun Legenda(cor: Color, texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(26.dp).background(cor, CircleShape))
        Spacer(Modifier.width(12.dp))
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun ListaEscolhas(titulo: String, itens: List<String>, cor: Color, fundo: Color, icone: ImageVector) {
    androidx.compose.material3.Surface(
        shape = RoundedCornerShape(20.dp),
        color = fundo,
        border = BorderStroke(2.dp, cor),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icone, contentDescription = null, tint = cor, modifier = Modifier.size(34.dp))
                Spacer(Modifier.width(10.dp))
                Text(titulo, style = MaterialTheme.typography.headlineSmall, color = cor)
            }
            itens.forEach { Marcador(it, cor) }
        }
    }
}

@Composable
private fun LinhaComIcone(icone: ImageVector, cor: Color, rotulo: String, texto: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(icone, contentDescription = null, tint = cor, modifier = Modifier.size(26.dp).padding(top = 2.dp))
        Spacer(Modifier.width(10.dp))
        Column {
            Text(rotulo, style = MaterialTheme.typography.labelMedium, color = cor)
            Text(texto, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
