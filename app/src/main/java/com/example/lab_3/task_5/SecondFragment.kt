package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentFirstBinding
import com.example.lab_3.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        binding.button3.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_firstFragment)
        }
        binding.button4.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        return binding.root
    }
}