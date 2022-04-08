package kr.ac.tukorea.foodmap.room

import androidx.lifecycle.LiveData

class PostRepository(private val postDao: PostDao)  {
    val readAllPost: LiveData<List<Post>> = postDao.getAll()


    suspend fun addPost(post:Post){
        postDao.insert(post)
    }

    suspend fun deletePost(post:Post){
        postDao.delete(post)
    }

    suspend fun updatePost(post:Post){
        postDao.update(post)
    }

}