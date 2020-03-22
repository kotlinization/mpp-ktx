package com.github.mikibemiki.android.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.mikibemiki.android.example.service.ExampleServiceBound
import kotlinx.android.synthetic.main.activity_bound_service.*
import kotlinx.coroutines.launch

class BoundServiceActivity : AppCompatActivity() {

    private val exampleServiceOne by lazy {
        ExampleServiceBound(this, lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
        generate.setOnClickListener {
            lifecycleScope.launch {
                generatedValue.text = exampleServiceOne {
                    generateString()
                }
            }
        }
    }
}