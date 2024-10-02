package suchetha.meka.cinemafinder.hilt.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}