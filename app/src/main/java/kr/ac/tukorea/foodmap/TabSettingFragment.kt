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
import kr.ac.tukorea.foodmap.room.PostViewModel


class TabSettingFragment : Fragment() {
    private lateinit var mPostViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_tab_setting, container, false)
        var count_tv:TextView =  view.findViewById(R.id.countTv);
        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mPostViewModel.getCount!!.observe(viewLifecycleOwner, Observer { count ->
            count_tv.text = count.toString()
            Log.d("test",count.toString())
        })
        return view
    }

}