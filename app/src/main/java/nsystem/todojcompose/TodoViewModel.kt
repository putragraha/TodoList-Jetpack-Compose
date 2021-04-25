package nsystem.todojcompose

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nsystem.todojcompose.list.Todo
import nsystem.todojcompose.taskrepository.TaskRepository

class TodoViewModel(context: Context): ViewModel() {

    var todoItems by mutableStateOf(listOf<Todo>())
        private set

    private val taskRepository = TaskRepository(context)

    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            todoItems = taskRepository.getTasks()
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(todo)
            todoItems = taskRepository.getTasks()
        }
    }

    fun removeTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.removeTask(todo)
            todoItems = taskRepository.getTasks()
        }
    }
}