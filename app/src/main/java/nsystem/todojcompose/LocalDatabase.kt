package nsystem.todojcompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nsystem.todojcompose.taskrepository.TaskDao
import nsystem.todojcompose.taskrepository.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        private const val DB_NAME = "task_db"

        @Volatile
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    LocalDatabase::class.java,
                    DB_NAME
                ).build()
            }
        }
    }
}