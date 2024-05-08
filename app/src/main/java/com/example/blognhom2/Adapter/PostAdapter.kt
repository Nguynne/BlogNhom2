package com.example.blognhom2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blognhom2.Fragment.PostContentFragment
import com.example.blognhom2.R
import com.example.blognhom2.model.Post
import java.util.Date

class PostAdapter(var postList : List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var genericList : MutableList<Post> = postList as MutableList<Post>

    inner class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val postImage: ImageView = itemView.findViewById(R.id.articleImage)
        val postTitle: TextView = itemView.findViewById(R.id.articleTitle)
        var postTime: TextView = itemView.findViewById(R.id.articleDateTime)
        var postCategory: TextView = itemView.findViewById(R.id.articleCategories)
        val postContent : TextView = itemView.findViewById(R.id.articleDescription)
        val postLayout : ConstraintLayout = itemView.findViewById(R.id.postLayout)
    }
    public fun setFilteredList(filteredList: List<Post>){
        this.genericList = filteredList as MutableList<Post>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return genericList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.apply {
            holder.postLayout.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim))
            holder.postTitle.text = genericList[position].title
            holder.postTime.text = genericList[position].time.toString()
            holder.postCategory.text = genericList[position].categories
            holder.postContent.text = genericList[position].postContent
            Glide.with(holder.itemView.context)
                .load(genericList[position].postImg)
                .into(holder.postImage)

            setOnClickListener {
                val post = genericList[position]
                val fragment = PostContentFragment() // Replace YourFragment with your actual fragment class
                fragment.setData(post)
                val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }
    }

}