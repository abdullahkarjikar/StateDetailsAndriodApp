package com.example.mad_ind05_karjikar_abdulla

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_ind05_karjikar_abdulla.databinding.ActivityMain2Binding
import java.io.IOException


class DestinationActivity : AppCompatActivity() {

    var binding: ActivityMain2Binding? = null

    // This function will be called when the Application segues from the recycler view.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting the binding object and contextView to root
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Retrieving the details using name which was set in Adapter class
        val stateName = intent.getStringExtra("stateName")
        val stateFlag = intent.getStringExtra("stateFlag")
        val stateMap = intent.getStringExtra("stateMap")
        val stateArea = intent.getStringExtra("stateArea")

        // Setting the state name and state area to the text view which is in the Destination Activity
        binding!!.stateNameTextView.text = stateName
        binding!!.areaTextView.text = stateArea

        // Setting State Flag to image View
        val stateFlagImage:Bitmap? = getBitmapImage(stateFlag!!)
        binding!!.stateFlagImageView.setImageBitmap(stateFlagImage)

        // Setting State Map to image View
        val stateMapImage:Bitmap? = getBitmapImage(stateMap!!)
        binding!!.stateMapImageView.setImageBitmap(stateMapImage)

        // This is used to display and navigate using back button on top left corner of the Destination Activity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    // This function returns the image to be displayed for flag and map.
    private fun getBitmapImage(imageName: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(assets.open(imageName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Call "finished" to initiate a return to the source.
    override fun onSupportNavigateUp(): Boolean {
        // Call "finished" to initiate a return to the source.
        finish()
        return super.onSupportNavigateUp()
    }


}