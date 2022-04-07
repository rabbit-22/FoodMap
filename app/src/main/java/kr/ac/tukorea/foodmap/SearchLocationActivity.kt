package kr.ac.tukorea.foodmap

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_search_location.*


class SearchLocationActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var slatLng:LatLng
    val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)

        val fm: FragmentManager = supportFragmentManager
        var mapFragment: MapFragment? = fm.findFragmentById(R.id.map) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fm.beginTransaction().add(R.id.map, mapFragment).commit()
        }
        mapFragment!!.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        sSaveBt.setOnClickListener{
            val mIntent = Intent(this, WritePostActivity::class.java)
            mIntent.putExtra(slatLng.latitude.toString(),slatLng.longitude.toString())
            setResult(Activity.RESULT_OK,mIntent)
            Toast.makeText(this, "위치가 등록되었습니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        val cameraPosition = naverMap.cameraPosition
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5666102, 126.9783881))
        naverMap.moveCamera(cameraUpdate)
        naverMap!!.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        uiSettings.isZoomGesturesEnabled = true
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = false
        uiSettings.isRotateGesturesEnabled = false

        var marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap
        naverMap.setOnMapClickListener { pointF, latLng ->
            marker.setPosition(latLng)
            slatLng = latLng
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PERMISSION_GRANTED
            ) {
                naverMap!!.locationTrackingMode = LocationTrackingMode.Follow
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}