package kr.ac.tukorea.foodmap

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_item.view.*
import kr.ac.tukorea.foodmap.room.Post
import org.w3c.dom.Text

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var  postList = emptyList<Post>() //

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_item,parent,false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.itemView.placeTitle.text = currentItem.placeTitle
        holder.itemView.date.text = currentItem.date
        holder.itemView.review.text = currentItem.review
        holder.itemView.setOnClickListener {
            listener!!.onItemClick(it, currentItem, position)
        }
    }
    fun setData(user:List<Post>){
        this.postList = user
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(v:View, data: Post, pos : Int)
    }
    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }


}