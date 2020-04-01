package com.github.mikibemiki.android.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.mikibemiki.android.example.service.ExampleServiceBound
import com.github.mikibemiki.mppktx.service.BoundService.Companion.withService
import kotlinx.android.synthetic.main.activity_bound_service.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BoundServiceActivity : AppCompatActivity() {

    private val exampleServiceOne by lazy {
        ExampleServiceBound(this, lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
        generate.setOnClickListener {
            lifecycleScope.launch {
                withService({ ExampleServiceBound(application, it) }) { exampleServiceBound ->
                    generatedValue.text = exampleServiceBound.invokeDelayed {
                        generateString()
                    }
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            exampleServiceOne.mapFlow {
                timeFlow
            }.collect {
                withContext(Main) {
                    time.text = it
                }
            }
        }
    }
}