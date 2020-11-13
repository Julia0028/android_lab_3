package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        binding.button5.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        binding.button6.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_thirdFragment_to_secondFragment)
        }
        return binding.root
    }
}