package nsystem.todojcompose.todo

data class Todo(
    val id: Int?,
    val description: String,
    val done: Boolean,
    val priority: Int
)