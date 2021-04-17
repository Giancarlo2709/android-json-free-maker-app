package com.example.jsonfreemakerapp.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.adapter.RVPhotoAdapter
import com.example.jsonfreemakerapp.config.ClientConfig
import com.example.jsonfreemakerapp.data.JsonFreeMakerService
import com.example.jsonfreemakerapp.databinding.FragmentPhotosBinding
import com.example.jsonfreemakerapp.databinding.FragmentPostBinding
import com.example.jsonfreemakerapp.model.PhotoResponse
import com.example.jsonfreemakerapp.model.PostResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [PhotosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhotosFragment : Fragment() {

    private lateinit var binding : FragmentPhotosBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance(): PhotosFragment = PhotosFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = JsonFreeMakerService(config = ClientConfig())

        CoroutineScope(Dispatchers.IO).launch {
            service.getPhotos().enqueue(object: Callback<List<PhotoResponse>> {
                override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<List<PhotoResponse>>, response: Response<List<PhotoResponse>>
                ) {
                    if (response.isSuccessful) {
                        showPhotos(response.body()!!)
                    }
                }
            })
        }
    }

    private fun showPhotos(photos: List<PhotoResponse>) {
        photos?.let {
            binding.rvPhotosList.adapter = RVPhotoAdapter(it)
            binding.rvPhotosList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}