package suchetha.meka.cinemafinder.hilt.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")
data class Result(
    @SerializedName("adult") @ColumnInfo(name = "adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") @ColumnInfo(name = "backdrop_path") val backdropPath: String = "",
    @PrimaryKey @SerializedName("id") @ColumnInfo(name = "id") val id: Int = -1,
    @SerializedName("original_language") @ColumnInfo(name = "original_language") val originalLanguage: String = "",
    @SerializedName("original_title") @ColumnInfo(name = "original_title") val originalTitle: String = "",
    @SerializedName("overview") @ColumnInfo(name = "overview") val overview: String = "",
    @SerializedName("popularity") @ColumnInfo(name = "popularity") val popularity: Double = 0.0,
    @SerializedName("poster_path") @ColumnInfo(name = "poster_path") val posterPath: String = "",
    @SerializedName("release_date") @ColumnInfo(name = "release_date") val releaseDate: String = "",
    @SerializedName("title") @ColumnInfo(name = "title") val title: String = "",
    @SerializedName("video") @ColumnInfo(name = "video") val video: Boolean = false,
    @SerializedName("vote_average") @ColumnInfo(name = "vote_average") val voteAverage: Double = 0.0,
    @SerializedName("vote_count") @ColumnInfo(name = "vote_count") val voteCount: Int = -1,
    @SerializedName("name") @ColumnInfo(name = "name") val name: String = ""
)
