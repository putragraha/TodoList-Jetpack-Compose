package nsystem.todojcompose.list

data class Todo(
    val id: Int,
    val description: String,
    val isDone: Boolean,
    val priority: Int
)