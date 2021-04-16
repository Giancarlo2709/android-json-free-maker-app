package com.example.jsonfreemakerapp.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jsonfreemakerapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    companion object {
        fun newInstance(): CommentFragment = CommentFragment()
    }

}