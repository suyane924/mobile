import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.teste.ui.theme.TesteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    var result by remember { mutableStateOf("0") }
    var input by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf<Char?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = result, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        RowsAndColumns(
            onButtonClick = { value ->
                when (value) {
                    in "0".."9" -> input += value
                    "+", "-", "*", "/" -> {
                        operation = value.first()
                        result = input
                        input = ""
                    }
                    "=" -> {
                        result = calculate(result, input, operation)
                        input = ""
                        operation = null
                    }
                    "c" -> {
                        input = ""
                        result = "0"
                        operation = null
                    }
                }
            }
        )
    }
}

@Composable
fun RowsAndColumns(onButtonClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = { onButtonClick("1") }) {
                Text("1")
            }
            FilledTonalButton(onClick = { onButtonClick("2") }) {
                Text("2")
            }
            FilledTonalButton(onClick = { onButtonClick("3") }) {
                Text("3")
            }
            FilledTonalButton(onClick = { onButtonClick("+") }) {
                Text("+")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = { onButtonClick("4") }) {
                Text("4")
            }
            FilledTonalButton(onClick = { onButtonClick("5") }) {
                Text("5")
            }
            FilledTonalButton(onClick = { onButtonClick("6") }) {
                Text("6")
            }
            FilledTonalButton(onClick = { onButtonClick("-") }) {
                Text("-")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = { onButtonClick("7") }) {
                Text("7")
            }
            FilledTonalButton(onClick = { onButtonClick("8") }) {
                Text("8")
            }
            FilledTonalButton(onClick = { onButtonClick("9") }) {
                Text("9")
            }
            FilledTonalButton(onClick = { onButtonClick("*") }) {
                Text("*")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = { onButtonClick("c") }) {
                Text("c")
            }
            FilledTonalButton(onClick = { onButtonClick("0") }) {
                Text("0")
            }
            FilledTonalButton(onClick = { onButtonClick("=") }) {
                Text("=")
            }
            FilledTonalButton(onClick = { onButtonClick("/") }) {
                Text("/")
            }
        }
    }
}

fun calculate(operand1: String, operand2: String, operation: Char?): String {
    val num1 = operand1.toDoubleOrNull() ?: return "Error"
    val num2 = operand2.toDoubleOrNull() ?: return "Error"

    return when (operation) {
        '+' -> (num1 + num2).toString()
        '-' -> (num1 - num2).toString()
        '*' -> (num1 * num2).toString()
        '/' -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
        else -> "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun RowsAndColumnsPreview() {
    TesteTheme {
        Calculator()
    }
}
