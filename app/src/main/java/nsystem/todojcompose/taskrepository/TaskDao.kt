package nsystem.todojcompose.taskrepository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<TaskEntity>

    @Insert
    suspend fun insertTask(taskEntity: TaskEntity): Long

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}