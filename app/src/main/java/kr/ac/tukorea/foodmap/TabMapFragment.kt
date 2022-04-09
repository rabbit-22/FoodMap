package kr.ac.tukorea.foodmap


import android.Manifest
import android.content.Intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kr.ac.tukorea.foodmap.room.AppDatabase
import kr.ac.tukorea.foodmap.room.Post
import kr.ac.tukorea.foodmap.room.PostViewModel


class TabMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var  mPostViewModel: PostViewModel
    val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private var mapView: MapView? = null
    var permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_tab_map,
            container, false
        ) as ViewGroup
        mapView = rootView.findViewById<View>(R.id.navermap) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        return rootView
    }
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        this.naverMap.locationSource = locationSource

        mPostViewModel.readAllPost.observe(viewLifecycleOwner, Observer { postList ->
            drawMarker(postList)
        })
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.mapType = NaverMap.MapType.Basic
        naverMap!!.locationSource = locationSource

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        uiSettings.isZoomGesturesEnabled = true
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = false
        uiSettings.isRotateGesturesEnabled = false
        //건물 표시
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, false)
    }
    private fun drawMarker(postList: List<Post>) {
        postList.forEach { diary ->
            val marker = Marker()
            marker.position = LatLng(diary.mapX!!.toDouble(), diary.mapY!!.toDouble())
            marker.map = naverMap
            marker.icon =  OverlayImage.fromResource(R.drawable.ic_marker)
            marker.width = 100
            marker.height = 100

            marker.setOnClickListener {
                val intent = Intent(getActivity(), DetailPostActivity::class.java)
                intent.putExtra("id", diary.id)
                intent.putExtra("date", diary.date)
                intent.putExtra("review", diary.review)
                intent.putExtra("mapX", diary.mapX)
                intent.putExtra("mapY", diary.mapY)
                intent.putExtra("placeTitle", diary.placeTitle)
                startActivity(intent)
                true
            }
        }


    }
    override fun onStart() {
        var addr: String
        super.onStart()
        mapView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }


    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }
}