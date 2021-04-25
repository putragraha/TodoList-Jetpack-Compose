package nsystem.todojcompose.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nsystem.todojcompose.TodoViewModel
import nsystem.todojcompose.list.Todo

@Composable
fun CreateTodoScreen(
    todoViewModel: TodoViewModel?,
    onBackPressed: () -> Unit,
    onAddTaskFinished: () -> Unit,
) {
    val descriptionState = rememberSaveable { mutableStateOf("") }
    val priorityState = rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = descriptionState.value,
            onValueChange = { value -> descriptionState.value = value },
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(top = 8.dp))
        PriorityDropdown(priorityState)
        Spacer(Modifier.padding(top = 8.dp))
        AddButton(
            todoViewModel = todoViewModel,
            descriptionState = descriptionState,
            priorityState = priorityState,
            onAddTaskFinished = onAddTaskFinished,
        )
        Spacer(Modifier.padding(top = 8.dp))
        BackAndCancelButton(onBackPressed)
    }
}

@Composable
private fun AddButton(
    todoViewModel: TodoViewModel?,
    descriptionState: MutableState<String>,
    priorityState: MutableState<Int>,
    onAddTaskFinished: () -> Unit,
) {
    Button(
        onClick = {
            val todo = Todo(
                id = null,
                description = descriptionState.value,
                priority = priorityState.value,
                done = false
            )

            todoViewModel?.addTodo(todo)
            onAddTaskFinished()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Add")
    }
}

@Composable
private fun PriorityDropdown(priorityState: MutableState<Int>) {
    val expandState = rememberSaveable { mutableStateOf(false) }
    val priorities = listOf(0, 1, 2)

    Box(
        modifier = Modifier
            .clickable(onClick = { expandState.value = true })
            .height(50.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        Text(
            text = "Priority ${priorities[priorityState.value]}",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        )
        DropdownMenu(
            expanded = expandState.value,
            onDismissRequest = { expandState.value = false }
        ) {
            priorities.forEachIndexed { index, priority ->
                DropdownMenuItem(onClick = {
                    priorityState.value = index
                    expandState.value = false
                }) {
                    Text(text = "Priority $priority")
                }
            }
        }
    }
}

@Composable
private fun BackAndCancelButton(onBackPressed: () -> Unit) {
    Button(
        onClick = { onBackPressed() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
    ) {
        Text(
            text = "Cancel & Back",
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun PreviewCreateTodoScreen() {
    CreateTodoScreen(null, {}, {})
}