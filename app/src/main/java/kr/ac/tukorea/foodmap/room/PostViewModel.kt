package kr.ac.tukorea.foodmap.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application): AndroidViewModel(application)  {
    private val readAllPost: LiveData<List<Post>>
    private val repository: PostRepository

    init {
        val postDao = AppDatabase.getDatabase(application)!!.postDao()
        repository = PostRepository(postDao)
        readAllPost = repository.readAllPost
    }

    fun addPost(post:Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPost(post)
        }
    }

    fun deletePost(post:Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePost(post)
        }
    }

    fun updatePost(post:Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePost(post)
        }
    }

}