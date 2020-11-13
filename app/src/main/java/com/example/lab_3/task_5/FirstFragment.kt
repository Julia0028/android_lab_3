package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)

        binding.button2.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return binding.root
    }
}
