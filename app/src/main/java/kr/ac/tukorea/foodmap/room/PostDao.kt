package kr.ac.tukorea.foodmap.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg post: Post)

    @Delete
    suspend fun delete(vararg post: Post)

    @Update
    suspend fun update(vararg post: Post)

    @Query("SELECT * FROM post_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Post>>

}
