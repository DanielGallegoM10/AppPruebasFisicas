import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.apppruebasfisicas.login.PantallaLogin
import com.example.apppruebasfisicas.navegacion.Login
import com.example.apppruebasfisicas.navegacion.Principal
import com.example.apppruebasfisicas.pantallaPrincipal.PantallaPrincipal

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            PantallaLogin {
                navController.navigate(Principal)
            }
        }
        composable<Principal> {
            PantallaPrincipal()
        }
    }
}
