package kr.ac.tukorea.foodmap

import android.app.DatePickerDialog
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.naver.maps.geometry.LatLng
import kotlinx.android.synthetic.main.activity_write_post.*
import java.text.SimpleDateFormat
import java.util.*


class WritePostActivity : AppCompatActivity() {
    var mapX = null
    var mapY = null

    var currentTime:Date = Calendar.getInstance().time
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post)

        var format:SimpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일 ", Locale.getDefault())
        var current:String = format.format(currentTime)
        wDateTv.setText(current)
        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val rMapX: Intent? = result.data
                val rMapY: Intent? = result.data
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
                Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
        wBackBt.setOnClickListener{
            finish()
        }
    }
}