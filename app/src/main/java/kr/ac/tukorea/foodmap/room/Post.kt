package kr.ac.tukorea.foodmap.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "place_title") val placeTitle: String?,
    @ColumnInfo(name = "review") val review: String?,
    @ColumnInfo(name = "map_x") val mapX: Int?,
    @ColumnInfo(name = "map_y") val mapY: Int?,
)
