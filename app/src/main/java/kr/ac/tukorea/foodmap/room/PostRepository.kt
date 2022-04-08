package kr.ac.tukorea.foodmap.room

import androidx.lifecycle.LiveData

class PostRepository(private val postDao: PostDao)  {
    val readAllPost: LiveData<List<Post>> = postDao.getAll()
    val getCount:LiveData<Int> = postDao.getCount()

    suspend fun addPost(post:Post){
        postDao.insert(post)
    }

    fun deletePost(id:Int){
        postDao.deleteById(id)
    }

    suspend fun updatePost(post:Post){
        postDao.update(post)
    }



}