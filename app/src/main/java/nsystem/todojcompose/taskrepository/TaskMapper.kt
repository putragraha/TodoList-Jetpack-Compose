package nsystem.todojcompose.taskrepository

import nsystem.todojcompose.list.Todo

fun Todo.toTaskEntity() = TaskEntity(
    id = id,
    description = description,
    priority = priority,
    done = done
)

fun List<TaskEntity>.toTodos() = map { task ->
    Todo(
        id = task.id,
        description = task.description,
        priority = task.priority,
        done = task.done
    )
}