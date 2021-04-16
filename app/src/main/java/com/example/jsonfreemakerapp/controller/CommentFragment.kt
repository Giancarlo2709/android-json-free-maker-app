package com.example.jsonfreemakerapp.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.adapter.RVCommentAdapter
import com.example.jsonfreemakerapp.databinding.FragmentCommentBinding
import com.example.jsonfreemakerapp.databinding.FragmentPostBinding
import com.example.jsonfreemakerapp.model.CommentResponse
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    private lateinit var binding : FragmentCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Comments"
        binding = FragmentCommentBinding.inflate(inflater)
        return binding.root//inflater.inflate(R.layout.fragment_comment, container, false)
    }

    companion object {
        fun newInstance(): CommentFragment = CommentFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lstComments = arguments?.getSerializable("Comments")
        val adapter = RVCommentAdapter(lstComments as ArrayList<CommentResponse>)
        binding.rvCommentList.adapter = adapter
        binding.rvCommentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //Toast.makeText(context, arguments?.getSerializable("Comments").toString(), Toast.LENGTH_LONG).show()
    }

}