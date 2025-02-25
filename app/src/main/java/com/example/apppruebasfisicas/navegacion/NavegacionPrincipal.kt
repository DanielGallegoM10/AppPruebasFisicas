import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.example.apppruebasfisicas.entidades.DatosObj
import com.example.apppruebasfisicas.entidades.LoginObj
import com.example.apppruebasfisicas.login.PantallaLogin
import com.example.apppruebasfisicas.navegacion.Login
import com.example.apppruebasfisicas.navegacion.Principal
import com.example.apppruebasfisicas.navegacion.PruebasFisicas
import com.example.apppruebasfisicas.pantallaPrincipal.PantallaPrincipal
import com.example.apppruebasfisicas.pruebasFisicasLista.PruebasFisicasLista

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            PantallaLogin {
                idUsuario -> navController.navigate(Principal(idUsuario = idUsuario))
            }
        }
        composable<Principal> { backStackEntry ->
            val usuario: Principal = backStackEntry.toRoute()

            PantallaPrincipal(
                usuario.idUsuario,
                navigateToBack = {
                    navController.navigate(Login) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                },
                navigateToPruebasFisicas = { edad ->
                    navController.navigate(PruebasFisicas(edad = edad))
                }
            )
        }
        composable<PruebasFisicas> { backStackEntry ->
            val usuario: PruebasFisicas = backStackEntry.toRoute()
            PruebasFisicasLista(
                edad = usuario.edad,
                navigateToBack = {
                    navController.navigate(Principal) {
                        popUpTo(Principal) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

