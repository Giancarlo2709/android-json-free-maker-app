package com.example.jsonfreemakerapp.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.adapter.RVPostAdapter
import com.example.jsonfreemakerapp.config.ClientConfig
import com.example.jsonfreemakerapp.data.JsonFreeMakerService
import com.example.jsonfreemakerapp.databinding.FragmentPostBinding
import com.example.jsonfreemakerapp.model.CommentResponse
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
            //val adapter = RVPostAdapter(it)

            //binding.rvPostList.adapter = adapter
            binding.rvPostList.adapter = RVPostAdapter(it){
                item -> getComments(item.id)
            }
            binding.rvPostList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    private fun getComments(id: Int) {
        val service = JsonFreeMakerService(config = ClientConfig())

        CoroutineScope(Dispatchers.IO).launch {
            service.getCommentsById(id).enqueue(object: Callback<List<CommentResponse>> {
                override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<List<CommentResponse>>, response: Response<List<CommentResponse>>
                ) {
                    if (response.isSuccessful) {
                        showDetail(response.body()!!)
                        //Toast.makeText(context, response.body()!!.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun showDetail(body: List<CommentResponse>) {
        (activity as AppCompatActivity).supportActionBar?.title = "Comments"
        val commentFragment = CommentFragment.newInstance()
        val bundle = Bundle()
        var array = arrayListOf<CommentResponse>()
        array.addAll(body)
        bundle.putSerializable("Comments", array)
        commentFragment.arguments = bundle
        openFragment(commentFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


}