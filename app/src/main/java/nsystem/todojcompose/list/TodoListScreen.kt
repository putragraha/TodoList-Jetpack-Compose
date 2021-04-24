package nsystem.todojcompose.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen(items: List<Todo>) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = items) {
                TodoItem(todo = it)
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Row(modifier = Modifier.fillMaxWidth(1f)) {
        Text(
            text = "P${todo.priority} -",
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(2f)
        )
        Text(
            text = todo.description,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(13f)
        )
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = null,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    val items = listOf(
        Todo(1, "Finish Jetpack Compose Project", false, 0),
        Todo(2, "Finish Report Saldo", false, 0)
    )

    TodoListScreen(items)
}