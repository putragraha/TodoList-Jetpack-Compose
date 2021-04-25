package nsystem.todojcompose.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nsystem.todojcompose.R
import nsystem.todojcompose.TodoViewModel

@Composable
fun TodoListScreen(
    todoViewModel: TodoViewModel?,
    onAddNewTaskPressed: () -> Unit
) {
    val items = todoViewModel?.todoItems ?: emptyList()
    todoViewModel?.getTodos()

    Column {
        AddNewTaskButton(onAddNewTaskPressed)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = items) {
                TodoItem(
                    todoViewModel = todoViewModel,
                    todo = it,
                )
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
private fun AddNewTaskButton(
    onAddNewTaskPressed: () -> Unit
) {
    Button(
        onClick = { onAddNewTaskPressed() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text("Add New Task")
    }
}

@Composable
private fun TodoItem(
    todoViewModel: TodoViewModel?,
    todo: Todo
) {
    val deleteConfirmState = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .clickable { deleteConfirmState.value = true }
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
        if (deleteConfirmState.value) {
            DeleteConfirmationDialog(
                todoViewModel = todoViewModel,
                todo = todo,
                deleteConfirmState = deleteConfirmState,
            )
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    todoViewModel: TodoViewModel?,
    todo: Todo,
    deleteConfirmState: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { deleteConfirmState.value = false },
        title = { Text("Delete Task") },
        text = {
            Text("Are you sure you want to delete " +
                    "\"${todo.description}\"?")
        },
        confirmButton = {
            Button(
                onClick = {
                    todoViewModel?.removeTodo(todo)
                    deleteConfirmState.value = false
                }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    deleteConfirmState.value = false
                }) {
                Text("No")
            }
        }
    )
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    TodoListScreen(null) {}
}