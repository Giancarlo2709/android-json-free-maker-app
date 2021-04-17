package com.example.jsonfreemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.model.PhotoResponse
import com.squareup.picasso.Picasso

class RVPhotoAdapter (
    private val photoResponse: List<PhotoResponse>
) :  RecyclerView.Adapter<RVPhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(item)
    }

    override fun getItemCount() = photoResponse.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val post = photoResponse[position]
        holder.bind(post)
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photoResponse: PhotoResponse) {
            val photo = itemView.findViewById<ImageView>(R.id.image_photo)
            val txtPhoto = itemView.findViewById<TextView>(R.id.txt_photo)
            txtPhoto.text = photoResponse.title
            Picasso.get().load(photoResponse.url).into(photo)
        }
    }
}