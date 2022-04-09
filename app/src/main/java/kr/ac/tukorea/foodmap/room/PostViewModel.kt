package kr.ac.tukorea.foodmap.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PostViewModel(application: Application): AndroidViewModel(application)  {

    val readAllPost: LiveData<List<Post>>
    val getCount: LiveData<Int>?
    private val repository: PostRepository

    init {
        val postDao = AppDatabase.getDatabase(application)!!.postDao()
        repository = PostRepository(postDao)
        readAllPost = repository.readAllPost
        getCount = repository.getCount
    }
    fun addPost(post:Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPost(post)
        }
    }

    fun deletePost(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePost(id)
        }
    }

    fun updatePost(post:Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePost(post)
        }
    }



}