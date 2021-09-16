package nsystem.todojcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import nsystem.todojcompose.todo.screen.CreateTodoScreen
import nsystem.todojcompose.todo.screen.TodoListScreen
import nsystem.todojcompose.todo.TodoViewModel

class Route {

    companion object {

        const val TODO_LIST = "todoList"

        const val CREATE_TODO = "createTodo"
    }
}

@Composable
fun AppRoute(todoViewModel: TodoViewModel?) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Route.TODO_LIST
    ) {
        composable(Route.TODO_LIST) {
            TodoListScreen(
                todoViewModel = todoViewModel,
                onAddNewTaskPressed = {
                    navController.navigate(Route.CREATE_TODO)
                }
            )
        }
        composable(Route.CREATE_TODO) {
            CreateTodoScreen(
                todoViewModel = todoViewModel,
                onBackPressed = {
                    navController.navigate(Route.TODO_LIST)
                },
                onAddTaskFinished = {
                    navController.navigate(Route.TODO_LIST)
                }
            )
        }
    }
}