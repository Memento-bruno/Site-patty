package br.com.isildo.saude.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.isildo.saude.ui.theme.Cores

val FormaCartao = RoundedCornerShape(20.dp)

/** Estrutura comum das telas: faixa colorida com "Voltar" grande e título. */
@Composable
fun TelaBase(
    titulo: String,
    cor: Color,
    icone: ImageVector,
    onVoltar: () -> Unit,
    subtitulo: String? = null,
    textoVoltar: String = "Voltar ao início",
    conteudo: LazyListScope.() -> Unit,
) {
    Column(Modifier.fillMaxSize().background(Cores.Fundo)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(cor)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Button(
                onClick = onVoltar,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = cor),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
                modifier = Modifier.heightIn(min = 56.dp),
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(28.dp))
                Spacer(Modifier.width(8.dp))
                Text(textoVoltar, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.size(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icone, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(titulo, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                    if (subtitulo != null) {
                        Text(subtitulo, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.92f))
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            conteudo()
            item { Spacer(Modifier.navigationBarsPadding()) }
        }
    }
}

@Composable
fun Cartao(
    modifier: Modifier = Modifier,
    fundo: Color = Cores.Cartao,
    borda: Color = Cores.Borda,
    conteudo: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = FormaCartao,
        color = fundo,
        border = BorderStroke(1.5.dp, borda),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp), content = conteudo)
    }
}

@Composable
fun TituloSecao(texto: String, cor: Color = Cores.Texto, modifier: Modifier = Modifier) {
    Text(
        texto,
        style = MaterialTheme.typography.headlineSmall,
        color = cor,
        modifier = modifier.padding(top = 10.dp).semantics { heading() },
    )
}

@Composable
fun TextoCorpo(texto: String, cor: Color = Cores.Texto, negrito: Boolean = false) {
    Text(
        texto,
        style = MaterialTheme.typography.bodyLarge,
        color = cor,
        fontWeight = if (negrito) FontWeight.Bold else null,
    )
}

@Composable
fun Marcador(texto: String, cor: Color = Cores.Azul) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            Modifier
                .padding(top = 10.dp)
                .size(10.dp)
                .background(cor, CircleShape),
        )
        Spacer(Modifier.width(12.dp))
        Text(texto, style = MaterialTheme.typography.bodyLarge, color = Cores.Texto)
    }
}

@Composable
fun ListaNumerada(itens: List<String>, cor: Color) {
    itens.forEachIndexed { i, texto ->
        Row(verticalAlignment = Alignment.Top) {
            Box(
                Modifier.size(34.dp).background(cor, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text("${i + 1}", color = Color.White, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.width(12.dp))
            Text(texto, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 2.dp))
        }
    }
}

@Composable
fun CaixaAviso(titulo: String, texto: String, cor: Color, corClara: Color, icone: ImageVector) {
    Surface(shape = FormaCartao, color = corClara, border = BorderStroke(2.dp, cor), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(18.dp)) {
            Icon(icone, contentDescription = null, tint = cor, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(14.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(titulo, style = MaterialTheme.typography.titleLarge, color = cor)
                Text(texto, style = MaterialTheme.typography.bodyLarge, color = Cores.Texto)
            }
        }
    }
}

@Composable
fun LinhaInfo(rotulo: String, valor: String) {
    Column {
        Text(rotulo, style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
        Text(valor, style = MaterialTheme.typography.bodyLarge, color = Cores.Texto)
    }
}

@Composable
fun Etiqueta(texto: String, cor: Color, fundo: Color) {
    Text(
        texto,
        style = MaterialTheme.typography.labelMedium,
        color = cor,
        modifier = Modifier
            .background(fundo, RoundedCornerShape(50))
            .border(1.5.dp, cor, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp),
    )
}

/** Cartão que abre e fecha com um toque, com aviso claro de "Toque para ver mais". */
@Composable
fun CartaoExpansivel(
    titulo: String,
    cor: Color,
    subtitulo: String? = null,
    topo: (@Composable RowScope.() -> Unit)? = null,
    abertoInicial: Boolean = false,
    conteudo: @Composable ColumnScope.() -> Unit,
) {
    var aberto by rememberSaveable(titulo) { mutableStateOf(abertoInicial) }
    Surface(
        shape = FormaCartao,
        color = Cores.Cartao,
        border = BorderStroke(if (aberto) 2.5.dp else 1.5.dp, if (aberto) cor else Cores.Borda),
        modifier = Modifier.fillMaxWidth().animateContentSize(),
    ) {
        Column {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable(role = Role.Button) { aberto = !aberto }
                    .padding(18.dp)
                    .semantics { stateDescription = if (aberto) "aberto" else "fechado" },
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                if (topo != null) Row(verticalAlignment = Alignment.CenterVertically, content = topo)
                Text(titulo, style = MaterialTheme.typography.titleLarge, color = Cores.Texto)
                if (subtitulo != null) Text(subtitulo, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        if (aberto) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null, tint = cor, modifier = Modifier.size(30.dp),
                    )
                    Text(
                        if (aberto) "Fechar" else "Toque para ver mais",
                        style = MaterialTheme.typography.labelLarge, color = cor,
                    )
                }
            }
            if (aberto) {
                Column(
                    Modifier.padding(start = 18.dp, end = 18.dp, bottom = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    content = conteudo,
                )
            }
        }
    }
}

/** Linha grande para marcar algo como feito (remédio tomado, tarefa concluída). */
@Composable
fun LinhaMarcar(
    titulo: String,
    detalhe: String?,
    marcado: Boolean,
    cor: Color,
    onMudar: (Boolean) -> Unit,
    textoMarcado: String = "Feito",
    textoDesmarcado: String = "Marcar",
    esquerda: (@Composable () -> Unit)? = null,
) {
    Surface(
        shape = FormaCartao,
        color = if (marcado) Cores.OkClaro else Cores.Cartao,
        border = BorderStroke(if (marcado) 2.dp else 1.5.dp, if (marcado) Cores.Ok else Cores.Borda),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 88.dp)
            .clickable(role = Role.Checkbox) { onMudar(!marcado) }
            .semantics {
                contentDescription = titulo
                stateDescription = if (marcado) textoMarcado else "não marcado"
            },
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            if (esquerda != null) {
                esquerda()
                Spacer(Modifier.width(14.dp))
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(titulo, style = MaterialTheme.typography.titleMedium, color = Cores.Texto)
                if (detalhe != null) Text(detalhe, style = MaterialTheme.typography.bodyMedium, color = Cores.TextoSuave)
            }
            Spacer(Modifier.width(10.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier
                        .size(52.dp)
                        .background(if (marcado) Cores.Ok else Color.White, RoundedCornerShape(14.dp))
                        .border(3.dp, if (marcado) Cores.Ok else cor, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    if (marcado) Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(38.dp))
                }
                Text(
                    if (marcado) textoMarcado else textoDesmarcado,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (marcado) Cores.Ok else cor,
                )
            }
        }
    }
}

@Composable
fun BotaoContorno(texto: String, icone: ImageVector, cor: Color, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(2.dp, cor),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = cor, containerColor = Color.White),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        modifier = modifier.heightIn(min = 60.dp),
    ) {
        Icon(icone, contentDescription = null, modifier = Modifier.size(26.dp))
        Spacer(Modifier.width(8.dp))
        Text(texto, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun Rodape(texto: String) {
    Text(
        texto,
        style = MaterialTheme.typography.bodyMedium,
        color = Cores.TextoSuave,
        modifier = Modifier.padding(top = 8.dp),
    )
}
