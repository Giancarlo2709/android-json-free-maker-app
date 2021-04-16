package com.example.jsonfreemakerapp.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfreemakerapp.adapter.RVPostAdapter
import com.example.jsonfreemakerapp.config.ClientConfig
import com.example.jsonfreemakerapp.data.JsonFreeMakerService
import com.example.jsonfreemakerapp.databinding.FragmentPostBinding
import com.example.jsonfreemakerapp.model.PostResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {

    private lateinit var binding : FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance(): PostFragment = PostFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = JsonFreeMakerService(config = ClientConfig())

        CoroutineScope(Dispatchers.IO).launch {
            service.getPosts().enqueue(object: Callback<List<PostResponse>> {
                override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>
                ) {
                    if (response.isSuccessful) {
                        showPosts(response.body()!!)
                    }
                }
            })
        }
    }

    private fun showPosts(posts: List<PostResponse>) {
        posts?.let {
            val adapter = RVPostAdapter(it)

            binding.rvPostList.adapter = adapter
            binding.rvPostList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }




}