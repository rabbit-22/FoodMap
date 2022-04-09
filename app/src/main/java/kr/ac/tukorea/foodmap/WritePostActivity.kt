package kr.ac.tukorea.foodmap

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_write_post.*
import kr.ac.tukorea.foodmap.room.Post
import kr.ac.tukorea.foodmap.room.PostViewModel
import java.text.SimpleDateFormat
import java.util.*


class WritePostActivity : AppCompatActivity() {
    private lateinit var mPostViewModel: PostViewModel
    var mapX:Double = 0.0
    var mapY:Double = 0.0

    var currentTime:Date = Calendar.getInstance().time
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post)
        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        var format:SimpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        var current:String = format.format(currentTime)
        wDateTv.setText(current)
        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val rMapX = result.data!!.getDoubleExtra("x",0.0)
                val rMapY = result.data!!.getDoubleExtra("y",0.0)
                mapX = rMapX
                mapY = rMapY
            }
        }
        wDatell.setOnClickListener{
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    wDateTv.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
                }
            },year, month, date)
            dlg.show()
        }
        wSearchBt.setOnClickListener {
            var intent = Intent(applicationContext, SearchLocationActivity::class.java)
            getResultText.launch(intent)
        }
        wSaveBt.setOnClickListener {
            if(mapX === null){
                Toast.makeText(this, "위치를 등록해주세요", Toast.LENGTH_SHORT).show()
            }else{
                insertDataToDatabase()
                finish()
            }

        }
        wBackBt.setOnClickListener{
            finish()
        }

    }
    private fun insertDataToDatabase(){
        val review = wReviewEt.text.toString()
        val placeTitle = wPlaceEt.text.toString()
        val date = wDateTv.text.toString()
        val post = Post(0, placeTitle, review, mapX!!.toDouble(), mapY!!.toDouble(),date)
        mPostViewModel.addPost(post)
        Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        var dlgBack = AlertDialog.Builder(this)
        dlgBack.setMessage("저장되지 않았습니다. 작성중인 글을 취소하시겠습니까?")
        dlgBack.setPositiveButton("작성취소"){dialog, which ->
            finish()
        }

        dlgBack.setNegativeButton("취소"){ dialog, which ->
            dialog.cancel()
        }
        dlgBack.show()
    }
}