package nsystem.todojcompose.taskrepository

import android.content.Context
import nsystem.todojcompose.LocalDatabase
import nsystem.todojcompose.list.Todo

class TaskRepository(context: Context) {

    private val localDatabase by lazy {
        LocalDatabase.getInstance(context)
    }

    suspend fun getTasks() = localDatabase.taskDao().getTasks().toTodos()

    suspend fun addTask(todo: Todo) {
        localDatabase.taskDao().insertTask(todo.toTaskEntity())
    }

    suspend fun removeTask(todo: Todo) {
        localDatabase.taskDao().deleteTask(todo.toTaskEntity())
    }
}