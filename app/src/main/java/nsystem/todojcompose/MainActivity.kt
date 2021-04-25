package nsystem.todojcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.ViewModelProviders
import nsystem.todojcompose.common.ViewModelFactory
import nsystem.todojcompose.todo.TodoViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: TodoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel(this)
        setContent {
            MaterialTheme {
                AppRoute(viewModel)
            }
        }
    }

    private fun createViewModel(activity: MainActivity): TodoViewModel {
        val viewModelFactory = ViewModelFactory(activity.applicationContext)

        return ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(TodoViewModel::class.java)
    }
}