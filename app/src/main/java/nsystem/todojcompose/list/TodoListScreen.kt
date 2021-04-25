package nsystem.todojcompose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nsystem.todojcompose.R

@Composable
fun TodoListScreen(
    onAddNewTaskPressed: () -> Unit
) {
    val items = listOf(
        Todo(1, "Finish Jetpack Compose Project", true, 2),
        Todo(2, "Finish Report Saldo", false, 0)
    )

    Column {
        Button(
            onClick = { onAddNewTaskPressed() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text("Add New Task")
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = items) {
                TodoItem(todo = it)
                Divider(
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                )
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(8.dp)
    ) {
        val color = when(todo.priority) {
            2 -> Color.Green
            1 -> Color.Yellow
            else -> Color.Red
        }
        val checkedState = rememberSaveable { mutableStateOf(todo.done) }
        Icon(
            painter = painterResource(id = R.drawable.ic_flag),
            contentDescription = null,
            tint = color
        )
        Text(
            text = todo.description,
            modifier = Modifier.padding(start = 8.dp)
        )
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    TodoListScreen {}
}