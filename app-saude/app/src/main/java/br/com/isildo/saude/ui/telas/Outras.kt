package br.com.isildo.saude.ui.telas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import br.com.isildo.saude.data.Condicoes
import br.com.isildo.saude.data.Consultas
import br.com.isildo.saude.data.Fontes
import br.com.isildo.saude.data.Pessoa
import br.com.isildo.saude.data.Progresso
import br.com.isildo.saude.data.ProximosPassos
import br.com.isildo.saude.data.Remedios
import br.com.isildo.saude.lembretes.Lembretes
import br.com.isildo.saude.ui.CaixaAviso
import br.com.isildo.saude.ui.Cartao
import br.com.isildo.saude.ui.CartaoExpansivel
import br.com.isildo.saude.ui.Etiqueta
import br.com.isildo.saude.ui.FormaCartao
import br.com.isildo.saude.ui.LinhaInfo
import br.com.isildo.saude.ui.LinhaMarcar
import br.com.isildo.saude.ui.ListaNumerada
import br.com.isildo.saude.ui.Marcador
import br.com.isildo.saude.ui.Rodape
import br.com.isildo.saude.ui.TelaBase
import br.com.isildo.saude.ui.TituloSecao
import br.com.isildo.saude.ui.theme.Cores
import java.time.format.DateTimeFormatter

// ---------------------------------------------------------------------------
// Minha saúde
// ---------------------------------------------------------------------------

@Composable
fun TelaSaude(onVoltar: () -> Unit) {
    TelaBase(
        titulo = "Minha saúde",
        subtitulo = "As condições explicadas de forma simples",
        cor = Cores.Saude,
        icone = Icons.Filled.Favorite,
        onVoltar = onVoltar,
    ) {
        item {
            Cartao {
                Text(Pessoa.NOME, style = MaterialTheme.typography.titleLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(Modifier.weight(1f)) { LinhaInfo("Idade", "${Pessoa.idade()} anos") }
                    Column(Modifier.weight(1f)) { LinhaInfo("Nascimento", Pessoa.NASCIMENTO.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(Modifier.weight(1f)) { LinhaInfo("Altura", Pessoa.ALTURA) }
                    Column(Modifier.weight(1f)) { LinhaInfo("Peso", Pessoa.PESO) }
                }
                LinhaInfo("IMC", Pessoa.IMC)
            }
        }
        item { TituloSecao("Condições de saúde", Cores.Saude) }
        item { Text("Toque em cada uma para entender melhor e ver o que ajuda.", style = MaterialTheme.typography.bodyLarge) }
        Condicoes.lista.forEach { c ->
            item(key = c.nome) {
                CartaoExpansivel(
                    titulo = c.nome,
                    subtitulo = c.emPalavrasSimples,
                    cor = Cores.Saude,
                    topo = if (c.emInvestigacao) {
                        { Etiqueta("Em acompanhamento", Cores.Atencao, Cores.AtencaoClaro) }
                    } else null,
                ) {
                    Text("O que se sabe", style = MaterialTheme.typography.titleMedium, color = Cores.Saude)
                    c.detalhes.forEach { Marcador(it, Cores.Saude) }
                    Text("O que ajuda", style = MaterialTheme.typography.titleMedium, color = Cores.Comida)
                    c.oQueAjuda.forEach { Marcador(it, Cores.Comida) }
                }
            }
        }
        item { TituloSecao("Outras informações", Cores.Saude) }
        item {
            Cartao {
                LinhaInfo("Alergias", Pessoa.ALERGIAS)
                LinhaInfo("Família", Pessoa.HISTORIA_FAMILIAR)
                LinhaInfo("Disposição", Pessoa.ESTADO_FUNCIONAL)
                Text("Antecedentes", style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
                Pessoa.antecedentes.forEach { Marcador(it, Cores.Saude) }
            }
        }
        item { Rodape("Fontes: ${Fontes.HISTORICO} e ${Fontes.PLANO}.") }
    }
}

// ---------------------------------------------------------------------------
// O que falta fazer
// ---------------------------------------------------------------------------

@Composable
fun TelaPassos(onVoltar: () -> Unit) {
    val progresso = Progresso.de(LocalContext.current)
    val feitos = ProximosPassos.lista.count { progresso.passoFeito(it.id) }
    TelaBase(
        titulo = "O que falta fazer",
        subtitulo = "$feitos de ${ProximosPassos.lista.size} concluídos",
        cor = Cores.Passos,
        icone = Icons.Filled.AssignmentTurnedIn,
        onVoltar = onVoltar,
    ) {
        item {
            Text(
                "Quando resolver um item, toque nele para marcar como feito.",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        ProximosPassos.lista.forEach { p ->
            item(key = p.id) {
                LinhaMarcar(
                    titulo = p.titulo,
                    detalhe = p.detalhe,
                    marcado = progresso.passoFeito(p.id),
                    cor = Cores.Passos,
                    onMudar = { progresso.marcarPasso(p.id, it) },
                )
            }
        }
        item { TituloSecao("O que fazer agora", Cores.Passos) }
        item { Cartao { ListaNumerada(ProximosPassos.focoDoPeriodo, Cores.Passos) } }
        item { Rodape("Lista montada a partir do ${Fontes.HISTORICO} e do ${Fontes.PLANO}.") }
    }
}

// ---------------------------------------------------------------------------
// Urgência e ficha médica
// ---------------------------------------------------------------------------

@Composable
fun TelaUrgencia(onVoltar: () -> Unit) {
    val context = LocalContext.current
    TelaBase(
        titulo = "Urgência e ficha médica",
        cor = Cores.Urgencia,
        icone = Icons.Filled.LocalHospital,
        onVoltar = onVoltar,
    ) {
        item {
            Button(
                onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:192"))) },
                colors = ButtonDefaults.buttonColors(containerColor = Cores.Urgencia, contentColor = Color.White),
                modifier = Modifier.fillMaxWidth().heightIn(min = 76.dp),
            ) {
                Icon(Icons.Filled.Call, contentDescription = null, modifier = Modifier.size(34.dp))
                Spacer(Modifier.width(12.dp))
                Text("Ligar para o SAMU (192)", style = MaterialTheme.typography.titleLarge)
            }
        }
        item {
            CaixaAviso(
                "Fraqueza forte na perna",
                "Se a perna ficar fraca de repente ou não conseguir mexer a perna, vá ao pronto-socorro. Leve a ressonância da coluna. (Orientação do ortopedista.)",
                Cores.Urgencia, Cores.AlertaClaro, Icons.Filled.Warning,
            )
        }
        item {
            CaixaAviso(
                "Exame ou procedimento com jejum",
                "Faça jejum só quando pedirem, e siga a orientação da equipe sobre os remédios daquele dia.",
                Cores.Atencao, Cores.AtencaoClaro, Icons.Filled.Info,
            )
        }

        item { TituloSecao("Ficha para mostrar ao médico", Cores.Urgencia) }
        item {
            Surface(
                shape = FormaCartao,
                color = Color.White,
                border = BorderStroke(3.dp, Cores.Urgencia),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Badge, contentDescription = null, tint = Cores.Urgencia, modifier = Modifier.size(34.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(Pessoa.NOME, style = MaterialTheme.typography.titleLarge)
                    }
                    LinhaInfo(
                        "Nascimento / idade",
                        "${Pessoa.NASCIMENTO.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} · ${Pessoa.idade()} anos",
                    )
                    LinhaInfo("Alergias", Pessoa.ALERGIAS)
                    Text("Condições", style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
                    Condicoes.lista.forEach { Marcador(it.nome, Cores.Urgencia) }
                    Text("Remédios em uso", style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
                    Remedios.atuais.forEach { r ->
                        val horarios = Remedios.tomadas.filter { it.remedioId == r.id }.joinToString(" e ") { hora(it.horario) }
                        val comercial = if (r.nomeComercial != r.nome) " · ${r.nomeComercial}" else ""
                        Marcador("${r.nome}$comercial: ${r.dose.lowercase()}, $horarios", Cores.Urgencia)
                    }
                    Text("Médicos", style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
                    Consultas.equipeAtual.take(2).forEach { m ->
                        Marcador("${m.nome}, ${m.especialidade.lowercase()} (${m.local})", Cores.Urgencia)
                    }
                    Text("Antecedentes", style = MaterialTheme.typography.labelMedium, color = Cores.TextoSuave)
                    Pessoa.antecedentes.forEach { Marcador(it, Cores.Urgencia) }
                }
            }
        }
        item { Rodape("Fonte: ${Fontes.HISTORICO}.") }
    }
}

// ---------------------------------------------------------------------------
// Ajustes
// ---------------------------------------------------------------------------

@Composable
fun TelaAjustes(onVoltar: () -> Unit) {
    val context = LocalContext.current
    val progresso = Progresso.de(context)
    var podeNotificar by remember { mutableStateOf(podeNotificar(context)) }
    val ciclo = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(ciclo) {
        ciclo.repeatOnLifecycle(Lifecycle.State.RESUMED) { podeNotificar = podeNotificar(context) }
    }

    TelaBase(
        titulo = "Ajustes",
        cor = Cores.TextoSuave,
        icone = Icons.Filled.Settings,
        onVoltar = onVoltar,
    ) {
        item { TituloSecao("Tamanho da letra") }
        listOf(1.0f to "Normal", 1.15f to "Grande", 1.3f to "Muito grande").forEach { (escala, nome) ->
            item(key = nome) {
                val escolhido = kotlin.math.abs(progresso.escalaFonte - escala) < 0.01f
                Surface(
                    shape = FormaCartao,
                    color = if (escolhido) Cores.AzulClaro else Cores.Cartao,
                    border = BorderStroke(if (escolhido) 2.5.dp else 1.5.dp, if (escolhido) Cores.Azul else Cores.Borda),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 72.dp)
                        .clickable(role = Role.RadioButton) { progresso.mudarEscala(escala) },
                ) {
                    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = escolhido, onClick = null,
                            colors = RadioButtonDefaults.colors(selectedColor = Cores.Azul),
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(nome, style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }

        item { TituloSecao("Lembretes de remédio") }
        item {
            Cartao {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.NotificationsActive, contentDescription = null, tint = Cores.Azul, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "Avisar na hora de cada remédio",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                    )
                    Switch(
                        checked = progresso.lembretesLigados,
                        onCheckedChange = {
                            progresso.mudarLembretes(it)
                            Lembretes.agendarTodos(context)
                        },
                        colors = SwitchDefaults.colors(checkedTrackColor = Cores.Azul),
                    )
                }
                Text(
                    if (progresso.lembretesLigados) "Ligado. O celular avisa às " +
                        Remedios.tomadas.map { hora(it.horario) }.distinct().joinToString(", ") + "."
                    else "Desligado.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                if (progresso.lembretesLigados && !podeNotificar) {
                    CaixaAviso(
                        "Os avisos estão bloqueados",
                        "Toque no botão abaixo e ative as notificações do Minha Saúde.",
                        Cores.Alerta, Cores.AlertaClaro, Icons.Filled.Warning,
                    )
                    Button(
                        onClick = {
                            context.startActivity(
                                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                    .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName),
                            )
                        },
                        modifier = Modifier.fillMaxWidth().heightIn(min = 60.dp),
                    ) { Text("Abrir configurações de notificação", style = MaterialTheme.typography.labelLarge) }
                }
            }
        }

        item { TituloSecao("Sobre este aplicativo") }
        item {
            Cartao {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Search, contentDescription = null, tint = Cores.TextoSuave, modifier = Modifier.size(28.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("De onde vêm as informações", style = MaterialTheme.typography.titleMedium)
                }
                Marcador(Fontes.HISTORICO, Cores.TextoSuave)
                Marcador(Fontes.PLANO, Cores.TextoSuave)
                Text(
                    "O aplicativo organiza estes documentos para facilitar o dia a dia. Ele não substitui os médicos " +
                        "nem a nutricionista. Se houver diferença, vale sempre a receita e a orientação médica mais recente.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    "As marcações (remédios, água e tarefas) ficam guardadas só neste celular.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Cores.TextoSuave,
                )
            }
        }
    }
}

fun podeNotificar(context: android.content.Context): Boolean =
    Build.VERSION.SDK_INT < 33 ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
