import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.example.apppruebasfisicas.entidades.DatosObj
import com.example.apppruebasfisicas.entidades.LoginObj
import com.example.apppruebasfisicas.login.PantallaLogin
import com.example.apppruebasfisicas.navegacion.DetallePrueba
import com.example.apppruebasfisicas.navegacion.Login
import com.example.apppruebasfisicas.navegacion.MuestraNotas
import com.example.apppruebasfisicas.navegacion.Principal
import com.example.apppruebasfisicas.navegacion.PruebasFisicas
import com.example.apppruebasfisicas.pantallaDetallePrueba.PantallaDetallePrueba
import com.example.apppruebasfisicas.pantallaMuestraNotas.PantallaMuestraNotas
import com.example.apppruebasfisicas.pantallaPrincipal.PantallaPrincipal
import com.example.apppruebasfisicas.pruebasFisicasLista.PruebasFisicasLista
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

@SuppressLint("RestrictedApi")
@Composable
fun NavegacionPrincipal(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            PantallaLogin(themeMode, onThemeChange) {
                idUsuario -> navController.navigate(Principal(themeMode, idUsuario = idUsuario))
            }
        }
        composable<Principal> { backStackEntry ->
            val usuario: Principal = backStackEntry.toRoute()

            PantallaPrincipal(
                themeMode = themeMode,
                onThemeChange = onThemeChange,
                usuario.idUsuario,
                navigateToBack = {
                    navController.navigate(Login) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                },
                navigateToPruebasFisicas = { edad, sexo ->
                    navController.navigate(PruebasFisicas(themeMode, edad = edad, sexo = sexo, idUsuario = usuario.idUsuario))
                },
                navigateToMuestraNotas = { idUsuario ->
                    navController.navigate(MuestraNotas(themeMode, idUsuario = idUsuario))
                }
            )
        }
        composable<PruebasFisicas> { backStackEntry ->
            val usuario: PruebasFisicas = backStackEntry.toRoute()
            PruebasFisicasLista(
                themeMode = themeMode,
                onThemeChange = onThemeChange,
                edad = usuario.edad,
                navigateToBack = {
                    navController.navigate(Principal(themeMode, idUsuario = usuario.idUsuario)) {
                        popUpTo(Principal::class) {
                            inclusive = true
                        }
                    }
                },
                onItemSelected = { nombrePrueba ->
                    navController.navigate(DetallePrueba(themeMode, nombrePrueba = nombrePrueba, sexo = usuario.sexo, edad = usuario.edad, idUsuario = usuario.idUsuario))
                }
            )
        }
        composable<DetallePrueba> { backStackEntry ->
            val detallePrueba: DetallePrueba = backStackEntry.toRoute()
            PantallaDetallePrueba(
                themeMode = themeMode,
                onThemeChange = onThemeChange,
                nombrePrueba = detallePrueba.nombrePrueba,
                idUsuario = detallePrueba.idUsuario,
                edad = detallePrueba.edad,
                sexo = detallePrueba.sexo,

            ) {
                val previousEntry = navController.previousBackStackEntry
                val edad = previousEntry?.arguments?.getInt("edad") ?: 0
                navController.navigate(PruebasFisicas(themeMode, edad = edad, sexo = detallePrueba.sexo, idUsuario = detallePrueba.idUsuario))
            }
        }
        composable<MuestraNotas> { backStackEntry ->
            val muestraNotas: MuestraNotas = backStackEntry.toRoute()
            PantallaMuestraNotas(
                themeMode = themeMode,
                onThemeChange = onThemeChange,
                idUsuario = muestraNotas.idUsuario,
                navigateToBack = {
                    navController.navigate(Principal(themeMode, idUsuario = muestraNotas.idUsuario))
                }
            )
        }
    }
}