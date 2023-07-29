package com.kotlinization.mpp_ktx.android.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kotlinizer.android.example.databinding.ActivityMainBinding
import com.kotlinization.mpp_ktx.android.example.service.BoundServiceActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startService.setOnClickListener {
            startActivity(Intent(this, BoundServiceActivity::class.java))
        }
    }
}
