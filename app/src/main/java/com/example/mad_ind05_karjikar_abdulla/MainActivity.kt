package com.example.mad_ind05_karjikar_abdulla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_ind05_karjikar_abdulla.databinding.ActivityMainBinding
import layout.StateDetailsDataModel

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    // This function will be called when the Application starts.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting the binding object and contextView to root
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Loading details from MyAdapter class to which has the state details
        val adapter = MyAdapter(StateDetailsDataModel.stateList)
        binding?.rvStateDetails?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}