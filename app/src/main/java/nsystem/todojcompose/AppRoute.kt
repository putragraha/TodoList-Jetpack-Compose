package nsystem.todojcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nsystem.todojcompose.create.CreateTodoScreen
import nsystem.todojcompose.list.TodoListScreen

class Route {

    companion object {

        const val TODO_LIST = "todoList"

        const val CREATE_TODO = "createTodo"
    }
}

@Composable
fun AppRoute() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Route.TODO_LIST
    ) {
        composable(Route.TODO_LIST) { TodoListScreen() }
        composable(Route.CREATE_TODO) { CreateTodoScreen() }
    }
}