package com.example.cacaaotesouro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cacaaotesouro.ui.theme.cacaaotesouroTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            cacaaotesouroTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Caça ao Tesouro") })
                    }
                ) {
                    Navegacao(navController)
                }
            }
        }
    }
}

@Composable
fun Navegacao(navController: NavHostController) {
    NavHost(navController, startDestination = "inicio") {
        composable("inicio") { TelaInicial(navController) }
        composable("pista1") { TelaPista(navController, "Eu sou uma coisa que você usa todos os dias, mas nunca vê.", "pista2") }
        composable("pista2") { TelaPista(navController, "Eu posso ser quebrado, mas nunca tocado.", "pista3") }
        composable("pista3") { TelaPista(navController, "Eu tenho cidades, mas sem casas; eu tenho montanhas, mas sem árvores; e eu tenho água, mas sem peixes.", "tesouro") }
        composable("tesouro") { TelaTesouro(navController) }
    }
}

@Composable
fun TelaInicial(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo à Caça ao Tesouro!", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("pista1") }) {
            Text("Iniciar Caça ao Tesouro")
        }
    }
}

@Composable
fun TelaPista(navController: NavHostController, pista: String, proximaTela: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = pista, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { navController.navigate(proximaTela) }) {
                Text("Próxima Pista")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Voltar")
            }
        }
    }
}

@Composable
fun TelaTesouro(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Parabéns! Você encontrou o tesouro!", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("inicio") }) {
            Text("Voltar ao Início")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviaTelaInicial() {
    cacaaotesouroTheme {
        TelaInicial(navController = rememberNavController())
    }
}
