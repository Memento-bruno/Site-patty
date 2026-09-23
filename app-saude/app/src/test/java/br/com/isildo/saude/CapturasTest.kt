package br.com.isildo.saude

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
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
import br.com.isildo.saude.ui.theme.TemaSaude
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Gera imagens das telas em app-saude/capturas para conferência visual. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w393dp-h1400dp-xxhdpi")
class CapturasTest {

    @get:Rule
    val regra = createComposeRule()

    private fun capturar(nome: String, tela: @Composable () -> Unit) {
        regra.setContent { TemaSaude { tela() } }
        regra.onRoot().captureRoboImage("../capturas/$nome.png")
    }

    @Test fun inicio() = capturar("01-inicio") { TelaInicio {} }
    @Test fun hoje() = capturar("02-remedios-de-hoje") { TelaHoje {} }
    @Test fun remedios() = capturar("03-meus-remedios") { TelaRemedios {} }
    @Test fun comida() = capturar("04-alimentacao") { TelaComida({}, {}) }
    @Test fun cardapio() = capturar("05-cardapio") { TelaComidaSecao(SecaoComida.CARDAPIO) {} }
    @Test fun prato() = capturar("06-prato") { TelaComidaSecao(SecaoComida.PRATO) {} }
    @Test fun escolhas() = capturar("07-pode-evite") { TelaComidaSecao(SecaoComida.ESCOLHAS) {} }
    @Test fun doces() = capturar("08-doces") { TelaComidaSecao(SecaoComida.DOCES) {} }
    @Test fun exames() = capturar("09-exames") { TelaExames {} }
    @Test fun consultas() = capturar("10-consultas") { TelaConsultas {} }
    @Test fun saude() = capturar("11-minha-saude") { TelaSaude {} }
    @Test fun passos() = capturar("12-o-que-falta") { TelaPassos {} }
    @Test fun urgencia() = capturar("13-urgencia") { TelaUrgencia {} }
    @Test fun ajustes() = capturar("14-ajustes") { TelaAjustes {} }
}
