package com.example.jsonfreemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.model.CommentResponse


class RVCommentAdapter (
    private val commentResponse: ArrayList<CommentResponse>
) :  RecyclerView.Adapter<RVCommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(item)
    }

    override fun getItemCount() = commentResponse.size
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val post = commentResponse[position]
        holder.bind(post)
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(comment: CommentResponse) {
            val txtName = itemView.findViewById<TextView>(R.id.txt_com_name)
            val txtEmail = itemView.findViewById<TextView>(R.id.txt_com_email)
            val txtBody = itemView.findViewById<TextView>(R.id.txt_com_body)

            txtName.text = comment.name
            txtEmail.text = comment.email
            txtBody.text = comment.body
        }

    }
}