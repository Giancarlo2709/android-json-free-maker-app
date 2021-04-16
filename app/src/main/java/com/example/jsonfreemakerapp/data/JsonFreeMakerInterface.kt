package com.example.jsonfreemakerapp.data

import com.example.jsonfreemakerapp.model.CommentResponse
import com.example.jsonfreemakerapp.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonFreeMakerInterface {

    @GET("/posts")
    fun getPosts() : Call<List<PostResponse>>

    @GET("/posts/{id}/comments")
    fun getCommentsById(@Path("id") id: Int) : Call<List<CommentResponse>>
}