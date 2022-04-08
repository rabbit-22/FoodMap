package kr.ac.tukorea.foodmap

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tab_list.view.*
import kr.ac.tukorea.foodmap.room.Post
import kr.ac.tukorea.foodmap.room.PostViewModel

class TabListFragment : Fragment() {
    private lateinit var mUserViewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tab_list, container, false)
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mUserViewModel.readAllPost.observe(viewLifecycleOwner, Observer { post->
            adapter.setData(post)
        })

        adapter.setOnItemClickListener(object: ListAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Post, position: Int) {
                val intent = Intent(getActivity(), DetailPostActivity::class.java)
                intent.putExtra("id", data.id)
                intent.putExtra("date", data.date)
                intent.putExtra("review", data.review)
                intent.putExtra("mapX", data.mapX)
                intent.putExtra("mapY", data.mapY)
                intent.putExtra("placeTitle", data.placeTitle)
                startActivity(intent)
            }
        })

        return view
    }

}