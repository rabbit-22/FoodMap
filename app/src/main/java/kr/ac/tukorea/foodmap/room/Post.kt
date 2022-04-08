package kr.ac.tukorea.foodmap.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "place_title") val placeTitle: String?,
    @ColumnInfo(name = "review") val review: String?,
    @ColumnInfo(name = "map_x") val mapX: Double?,
    @ColumnInfo(name = "map_y") val mapY: Double?,
    @ColumnInfo(name = "date") val date: String?,
)
