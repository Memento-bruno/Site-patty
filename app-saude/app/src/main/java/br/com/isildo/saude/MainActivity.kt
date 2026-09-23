package br.com.isildo.saude

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import br.com.isildo.saude.data.Progresso
import br.com.isildo.saude.lembretes.Lembretes
import br.com.isildo.saude.ui.AppSaude
import br.com.isildo.saude.ui.telas.podeNotificar
import br.com.isildo.saude.ui.theme.TemaSaude

class MainActivity : ComponentActivity() {

    private val pedirNotificacao = registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val progresso = Progresso.de(this)
        Lembretes.criarCanal(this)
        Lembretes.agendarTodos(this)
        if (savedInstanceState == null && progresso.lembretesLigados && !podeNotificar(this) && Build.VERSION.SDK_INT >= 33) {
            pedirNotificacao.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        setContent {
            val base = LocalDensity.current
            // Tamanho da letra escolhido no app, somado ao do próprio celular.
            CompositionLocalProvider(
                LocalDensity provides Density(base.density, base.fontScale * progresso.escalaFonte),
            ) {
                TemaSaude { AppSaude() }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualiza marcações feitas pela notificação ("Já tomei") e a virada do dia.
        Progresso.de(this).recarregar()
    }
}
