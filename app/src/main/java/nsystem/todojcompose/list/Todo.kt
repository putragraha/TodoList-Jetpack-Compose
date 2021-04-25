package nsystem.todojcompose.list

data class Todo(
    val id: Int?,
    val description: String,
    val done: Boolean,
    val priority: Int
)