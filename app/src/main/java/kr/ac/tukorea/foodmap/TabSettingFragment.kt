package kr.ac.tukorea.foodmap

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_tab_setting.*
import kr.ac.tukorea.foodmap.room.PostDao
import kr.ac.tukorea.foodmap.room.PostRepository
import kr.ac.tukorea.foodmap.room.PostViewModel


class TabSettingFragment : Fragment() {
    private lateinit var mUserViewModel: PostViewModel
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_tab_setting, container, false)
        var id_tv = getView()?.findViewById<TextView>(R.id.count)
        mUserViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mUserViewModel.getCount.observe(viewLifecycleOwner, Observer { post->
            id_tv?.setText(post?.toString())
            count++
        })
       return view
    }

}