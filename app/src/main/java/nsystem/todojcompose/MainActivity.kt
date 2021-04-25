package nsystem.todojcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.rememberNavController
import nsystem.todojcompose.create.CreateTodoScreen
import nsystem.todojcompose.list.Todo
import nsystem.todojcompose.list.TodoListScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppRoute()
            }
        }
    }
}