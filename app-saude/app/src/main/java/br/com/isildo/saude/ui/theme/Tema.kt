package br.com.isildo.saude.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*
 * Paleta clara e de alto contraste, pensada para leitura fácil.
 * Cada seção tem uma cor própria, sempre acompanhada de ícone e texto.
 */
object Cores {
    val Fundo = Color(0xFFF7F3EC)
    val Cartao = Color(0xFFFFFFFF)
    val Texto = Color(0xFF1B1B1B)
    val TextoSuave = Color(0xFF4A4A4A)
    val Borda = Color(0xFFDDD5C8)

    val Azul = Color(0xFF1F5FA8)       // principal
    val AzulClaro = Color(0xFFE3EDF9)
    val Remedios = Color(0xFF1F5FA8)
    val RemediosClaro = Color(0xFFE3EDF9)
    val Comida = Color(0xFF2E7D32)
    val ComidaClaro = Color(0xFFE5F2E5)
    val Exames = Color(0xFF7B3FA0)
    val ExamesClaro = Color(0xFFF1E7F7)
    val Consultas = Color(0xFF00696F)
    val ConsultasClaro = Color(0xFFDDF1F2)
    val Saude = Color(0xFFB3261E)
    val SaudeClaro = Color(0xFFFBE4E2)
    val Passos = Color(0xFFA15C00)
    val PassosClaro = Color(0xFFFCEBD3)
    val Urgencia = Color(0xFFB3261E)

    val Manha = Color(0xFFB26A00)
    val ManhaClaro = Color(0xFFFFF0D6)
    val Tarde = Color(0xFFC0461B)
    val TardeClaro = Color(0xFFFDE5DA)
    val Noite = Color(0xFF3949AB)
    val NoiteClaro = Color(0xFFE6E8F7)

    val Ok = Color(0xFF2E7D32)
    val OkClaro = Color(0xFFE5F2E5)
    val Alerta = Color(0xFFB3261E)
    val AlertaClaro = Color(0xFFFBE4E2)
    val Atencao = Color(0xFF8A5A00)
    val AtencaoClaro = Color(0xFFFFF0D6)
}

private val tipografia = Typography(
    displaySmall = TextStyle(fontSize = 32.sp, lineHeight = 40.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontSize = 28.sp, lineHeight = 36.sp, fontWeight = FontWeight.Bold),
    headlineSmall = TextStyle(fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.Bold),
    titleLarge = TextStyle(fontSize = 22.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 20.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 19.sp, lineHeight = 28.sp),
    bodyMedium = TextStyle(fontSize = 17.sp, lineHeight = 25.sp),
    labelLarge = TextStyle(fontSize = 19.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
    labelMedium = TextStyle(fontSize = 16.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold),
)

@Composable
fun TemaSaude(conteudo: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Cores.Azul,
            onPrimary = Color.White,
            primaryContainer = Cores.AzulClaro,
            background = Cores.Fundo,
            onBackground = Cores.Texto,
            surface = Cores.Cartao,
            onSurface = Cores.Texto,
            onSurfaceVariant = Cores.TextoSuave,
            outline = Cores.Borda,
            error = Cores.Alerta,
        ),
        typography = tipografia,
        content = conteudo,
    )
}
