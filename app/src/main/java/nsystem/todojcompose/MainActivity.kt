package nsystem.todojcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import nsystem.todojcompose.list.Todo
import nsystem.todojcompose.list.TodoListScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val items = listOf(
                    Todo(1, "Finish Jetpack Compose Project", true, 2),
                    Todo(2, "Finish Report Saldo", false, 0)
                )

                TodoListScreen(items)
            }
        }
    }
}