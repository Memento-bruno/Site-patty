package br.com.isildo.saude.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.isildo.saude.ui.telas.SecaoComida
import br.com.isildo.saude.ui.telas.TelaAjustes
import br.com.isildo.saude.ui.telas.TelaComida
import br.com.isildo.saude.ui.telas.TelaComidaSecao
import br.com.isildo.saude.ui.telas.TelaConsultas
import br.com.isildo.saude.ui.telas.TelaExames
import br.com.isildo.saude.ui.telas.TelaHoje
import br.com.isildo.saude.ui.telas.TelaInicio
import br.com.isildo.saude.ui.telas.TelaPassos
import br.com.isildo.saude.ui.telas.TelaRemedios
import br.com.isildo.saude.ui.telas.TelaSaude
import br.com.isildo.saude.ui.telas.TelaUrgencia

object Rotas {
    const val INICIO = "inicio"
    const val HOJE = "hoje"
    const val REMEDIOS = "remedios"
    const val COMIDA = "comida"
    const val COMIDA_SECAO = "comida/{secao}"
    const val EXAMES = "exames"
    const val CONSULTAS = "consultas"
    const val SAUDE = "saude"
    const val PASSOS = "passos"
    const val URGENCIA = "urgencia"
    const val AJUSTES = "ajustes"

    fun comida(secao: SecaoComida) = "comida/${secao.name}"
}

private fun NavHostController.voltarAoInicio() {
    if (!popBackStack(Rotas.INICIO, inclusive = false)) navigate(Rotas.INICIO)
}

@Composable
fun AppSaude() {
    val nav = rememberNavController()
    val inicio = { nav.voltarAoInicio() }
    val ir: (String) -> Unit = { rota -> nav.navigate(rota) { launchSingleTop = true } }

    NavHost(navController = nav, startDestination = Rotas.INICIO) {
        composable(Rotas.INICIO) { TelaInicio(ir) }
        composable(Rotas.HOJE) { TelaHoje(inicio) }
        composable(Rotas.REMEDIOS) { TelaRemedios(inicio) }
        composable(Rotas.COMIDA) { TelaComida(inicio) { ir(Rotas.comida(it)) } }
        composable(Rotas.COMIDA_SECAO) { entrada ->
            val secao = SecaoComida.valueOf(entrada.arguments?.getString("secao") ?: SecaoComida.CARDAPIO.name)
            TelaComidaSecao(secao) { nav.popBackStack() }
        }
        composable(Rotas.EXAMES) { TelaExames(inicio) }
        composable(Rotas.CONSULTAS) { TelaConsultas(inicio) }
        composable(Rotas.SAUDE) { TelaSaude(inicio) }
        composable(Rotas.PASSOS) { TelaPassos(inicio) }
        composable(Rotas.URGENCIA) { TelaUrgencia(inicio) }
        composable(Rotas.AJUSTES) { TelaAjustes(inicio) }
    }
}
