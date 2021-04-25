package nsystem.todojcompose.taskrepository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val description: String,
    val done: Boolean,
    val priority: Int
)