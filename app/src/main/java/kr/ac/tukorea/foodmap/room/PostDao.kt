package kr.ac.tukorea.foodmap.room

import androidx.room.*

@Dao
interface PostDao {
    @Insert
    fun insert(vararg post: Post)

    @Delete
    fun delete(vararg post: Post)

    @Query("DELETE FROM post WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun updateUsers(vararg post: Post)

    @Query("SELECT * FROM post")
    fun getAll(): List<Post>

    @Query("SELECT * FROM post ORDER BY id DESC")
    fun select(): List<Post>
}
