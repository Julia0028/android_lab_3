package com.example.lab_3.task_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}