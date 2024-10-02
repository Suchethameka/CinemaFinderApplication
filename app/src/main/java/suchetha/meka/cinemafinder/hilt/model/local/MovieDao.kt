package suchetha.meka.cinemafinder.hilt.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    fun insertMovie(result: Result)

    @Query("Select * from movie")
    fun fetchMovie(): List<Result>
}