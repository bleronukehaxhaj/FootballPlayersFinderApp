package com.footballplayersfinderapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.footballplayersfinderapp.databinding.FragmentPlayerDetailsBinding

class PlayerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPlayerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val photo = bundle?.getInt("photo")
        val firstName = bundle?.getString("firstName")
        val lastName = bundle?.getString("lastName")
        val age = bundle?.getInt("age")

        if (photo != null) {
            binding.ivPhoto.setImageResource(photo)
        }
        binding.tvFirstName.text = firstName
        binding.tvLastName.text = lastName
        binding.tvAge.text = age.toString()
    }

}