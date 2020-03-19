package com.github.mikibemiki.android.example

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikibemiki.android.example.service.ExampleServiceBound

class BoundServiceActivity : AppCompatActivity() {

    private val viewModel: BoundServiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)

    }
}


private class BoundServiceViewModel(application: Application) : AndroidViewModel(application) {

    private val exampleService = ExampleServiceBound(application, viewModelScope)

}
