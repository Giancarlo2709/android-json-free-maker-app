package com.example.jsonfreemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.model.PostResponse

class RVPostAdapter (
    private val postResponse: List<PostResponse>
) :  RecyclerView.Adapter<RVPostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(item)
    }

    override fun getItemCount() = postResponse.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postResponse[position]
        holder.bind(post)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: PostResponse) {
            val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
            val txtBody = itemView.findViewById<TextView>(R.id.txt_body)
            txtTitle.text = post.title
            txtBody.text = post.body

            val button = itemView.findViewById<Button>(R.id.btn_comments)
            button.setOnClickListener {

            }

        }

    }
}