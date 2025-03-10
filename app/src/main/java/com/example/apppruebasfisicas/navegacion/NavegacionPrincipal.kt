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
import com.example.apppruebasfisicas.SplashScreen
import com.example.apppruebasfisicas.entidades.DatosObj
import com.example.apppruebasfisicas.entidades.LoginObj
import com.example.apppruebasfisicas.login.PantallaLogin
import com.example.apppruebasfisicas.navegacion.DetallePrueba
import com.example.apppruebasfisicas.navegacion.Login
import com.example.apppruebasfisicas.navegacion.MuestraNotas
import com.example.apppruebasfisicas.navegacion.Principal
import com.example.apppruebasfisicas.navegacion.PruebasFisicas
import com.example.apppruebasfisicas.navegacion.Splash
import com.example.apppruebasfisicas.pantallaDetallePrueba.PantallaDetallePrueba
import com.example.apppruebasfisicas.pantallaMuestraNotas.PantallaMuestraNotas
import com.example.apppruebasfisicas.pantallaPrincipal.PantallaPrincipal
import com.example.apppruebasfisicas.pruebasFisicasLista.PruebasFisicasLista
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

//Fichero en el que se trata la navegación de toda la app, siendo su punto de partida el splash

@SuppressLint("RestrictedApi")
@Composable
fun NavegacionPrincipal(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(navController)
        }
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
                navigateToPruebasFisicas = { edad, sexo, nombreUsuario ->
                    navController.navigate(PruebasFisicas(themeMode, nombreUsuario = nombreUsuario, edad = edad, sexo = sexo, idUsuario = usuario.idUsuario))
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
                    navController.navigate(DetallePrueba(themeMode, nombrePrueba = nombrePrueba, nombreUsuario = usuario.nombreUsuario, sexo = usuario.sexo, edad = usuario.edad, idUsuario = usuario.idUsuario))
                }
            )
        }
        composable<DetallePrueba> { backStackEntry ->
            val detallePrueba: DetallePrueba = backStackEntry.toRoute()
            PantallaDetallePrueba(
                themeMode = themeMode,
                onThemeChange = onThemeChange,
                nombrePrueba = detallePrueba.nombrePrueba,
                nombreAlumno = detallePrueba.nombreUsuario,
                edad = detallePrueba.edad,
                sexo = detallePrueba.sexo,
                idUsuario = detallePrueba.idUsuario,
            ) {
                navController.navigate(PruebasFisicas(themeMode, nombreUsuario = detallePrueba.nombreUsuario, edad = detallePrueba.edad, sexo = detallePrueba.sexo, idUsuario = detallePrueba.idUsuario))
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