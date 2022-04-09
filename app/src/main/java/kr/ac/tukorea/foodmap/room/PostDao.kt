package kr.ac.tukorea.foodmap.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg post: Post)

    @Query("DELETE FROM post_table WHERE id = :id")
    fun deleteById(vararg id: Int)

    @Update
    suspend fun update(vararg post: Post)

    @Query("SELECT * FROM post_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Post>>

    @Query("SELECT count(*) FROM post_table")
    fun getCount(): LiveData<Int>?
}
