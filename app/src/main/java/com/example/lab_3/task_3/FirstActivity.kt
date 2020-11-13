package com.example.lab_3.task_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityFirstBinding

class FirstActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}