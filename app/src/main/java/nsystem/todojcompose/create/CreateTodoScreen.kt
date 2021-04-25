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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateTodoScreen(
    onBackPressed: () -> Unit
) {
    val descriptionState = remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.padding(top = 8.dp))
        PriorityDropdown()
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Button(
            onClick = { /* Do something! */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add")
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
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
}

@Composable
fun PriorityDropdown() {
    val expandState = remember { mutableStateOf(false) }
    val selectedState = remember { mutableStateOf(0) }
    val priorities = listOf(0, 1, 2)

    Box(
        modifier = Modifier
            .clickable(onClick = { expandState.value = true })
            .height(50.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        Text(
            text = "Priority ${priorities[selectedState.value]}",
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
                    selectedState.value = index
                    expandState.value = false
                }) {
                    Text(text = "Priority $priority")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateTodoScreen() {
    CreateTodoScreen {}
}