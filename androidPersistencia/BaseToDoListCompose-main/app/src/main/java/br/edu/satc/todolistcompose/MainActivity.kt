package br.edu.satc.todolistcompose

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.*
import kotlinx.coroutines.launch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext

@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String
)

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas")
    suspend fun obterTodasTarefas(): List<Tarefa>

    @Insert
    suspend fun inserirTarefa(tarefa: Tarefa)

    @Query("DELETE FROM tarefas")
    suspend fun deletarTodas()
}


@Database(entities = [Tarefa::class], version = 1)
abstract class TarefaDatabase : RoomDatabase() {
    abstract fun tarefaDao(): TarefaDao
}


fun obterBancoDeDados(contexto: Context): TarefaDatabase {
    return Room.databaseBuilder(
        contexto.applicationContext,
        TarefaDatabase::class.java,
        "banco_de_tarefas"
    ).build()
}


@Composable
fun TelaTarefas(contexto: Context) {
    val escopo = rememberCoroutineScope()
    val bancoDeDados = remember { obterBancoDeDados(contexto) }
    var listaTarefas by remember { mutableStateOf(listOf<Tarefa>()) }
    var novoTituloTarefa by remember { mutableStateOf("") }
    var novaDescricaoTarefa by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        listaTarefas = bancoDeDados.tarefaDao().obterTodasTarefas()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Tarefas", style = MaterialTheme.typography.headlineMedium)


        OutlinedTextField(
            value = novoTituloTarefa,
            onValueChange = { novoTituloTarefa = it },
            label = { Text("Título da Tarefa") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = novaDescricaoTarefa,
            onValueChange = { novaDescricaoTarefa = it },
            label = { Text("Descrição da Tarefa") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
        )
        Button(onClick = {
            escopo.launch {

                val novaTarefa = Tarefa(titulo = novoTituloTarefa, descricao = novaDescricaoTarefa)
                bancoDeDados.tarefaDao().inserirTarefa(novaTarefa)
                listaTarefas = bancoDeDados.tarefaDao().obterTodasTarefas()
            }
        }) {
            Text("Adicionar Tarefa")
        }

        Spacer(modifier = Modifier.height(16.dp))


        listaTarefas.forEach { tarefa ->
            Text("${tarefa.titulo}: ${tarefa.descricao}")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            escopo.launch {
                bancoDeDados.tarefaDao().deletarTodas()
                listaTarefas = bancoDeDados.tarefaDao().obterTodasTarefas()
            }
        }) {
            Text("Deletar Todas as Tarefas")
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaTarefas(contexto = this)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VisualizacaoPadrao() {
    TelaTarefas(contexto = LocalContext.current)
}