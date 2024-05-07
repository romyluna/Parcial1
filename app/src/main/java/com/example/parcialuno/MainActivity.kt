package com.example.parcialuno



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcialuno.ui.theme.ParcialUnoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParcialUnoTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("welcome/{username}") { backStackEntry ->
                            WelcomeScreen(backStackEntry.arguments?.getString("username"))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Parcial Uno",
            modifier = Modifier.padding(vertical = 10.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = { Text("Ingresa tu correo") }
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            label = { Text("Ingresa tu contraseña") }
        )

        if (showError) {
            Text(
                text = "Correo o contraseña incorrectos",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                // Verificar correo y contraseña
                val isCredentialsValid = email == "pedro@pe.com.ar" && password == "abc123"
                if (isCredentialsValid) {
                    // Navegar a la pantalla de bienvenida y pasar el nombre de usuario como argumento
                    navController.navigate("welcome/pedro")
                } else {
                    showError = true
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(text = "Ingresar")
        }
    }
}

@Composable
fun WelcomeScreen(username: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido!",
            /*style = MaterialTheme.typography.h3,*/
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Hola, $username",
            /*style = MaterialTheme.typography.body1*/
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ParcialUnoTheme {
        LoginScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ParcialUnoTheme {
        WelcomeScreen(username = "Pedro Pe")
    }
}
