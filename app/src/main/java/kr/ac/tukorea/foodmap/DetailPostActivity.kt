package kr.ac.tukorea.foodmap



import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_detail_post.*
import kotlinx.android.synthetic.main.activity_write_post.*
import kr.ac.tukorea.foodmap.room.Post
import kr.ac.tukorea.foodmap.room.PostViewModel


class DetailPostActivity : AppCompatActivity() {
    private lateinit var mPostViewModel:PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)
        val id = intent.getIntExtra("id", 0)
        val mapX = intent.getDoubleExtra("mapX", 0.0)
        val mapY = intent.getDoubleExtra("mapY", 0.0)
        val review = intent.getStringExtra("review")
        val date = intent.getStringExtra("date")
        val placeTitle = intent.getStringExtra("placeTitle")
        dPlaceEt.setText(placeTitle.toString())
        dDateTv.setText(date.toString())
        dReviewEt.setText(review.toString())
        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        dBackBt.setOnClickListener {
            val placeName = dPlaceEt.text.toString()
            val review = dReviewEt.text.toString()
            val updatedPost = Post(id, placeName, review, mapX,mapY,date)
            mPostViewModel.updatePost(updatedPost)
            finish()
        }
       dDeleteBt.setOnClickListener {
           val builder = AlertDialog.Builder(this)
           builder.setPositiveButton("Yes"){ _, _ ->
               mPostViewModel.deletePost(id)
               Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_SHORT).show()
               finish()
           }
           builder.setNegativeButton("No") { _, _ ->
           }
           builder.setMessage("정말로 삭제하시겠습니까?")
           builder.create().show()
       }

    }

}