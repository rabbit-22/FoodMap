package kr.ac.tukorea.foodmap

import android.Manifest
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    val LOCATION_PERMISSION_REQUEST_CODE = 1000
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    var fragment:Fragment = TabMapFragment()
    var title: String = "FoodMap"
//    val db = AppDatabase.getInstance(applicationContext)
    var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        toolbar_text.setText(title)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)

        if (savedInstanceState == null) {
            var fragmentManager:FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.content_fragment_layout, fragment).commit()
        }
        fab.setOnClickListener{
            var intent = Intent(applicationContext, WritePostActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.tab_map-> {
                fragment = TabMapFragment()
                title = "FoodMap"
            }
            R.id.tab_list-> {
                fragment = TabListFragment()
                title = "List"
            }
            R.id.tab_setting-> {
                fragment = TabSettingFragment()
                title = "Setting"
            }
        }
        if (fragment != null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_fragment_layout, fragment)
            ft.commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }

    }
}