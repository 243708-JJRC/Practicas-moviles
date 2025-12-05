package com.jjuanrc.intents_camera_app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : Activity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            setBackgroundColor(Color.parseColor("#4A90E2"))

            setPadding(50, 50, 50, 50)
        }

        val btnBackground = GradientDrawable().apply {
            cornerRadius = 50f
            setColor(Color.WHITE)
            setStroke(5, Color.parseColor("#1B75D1"))
        }

        val btnAbrirCamara = Button(this).apply {
            text = "Abrir Cámara"
            textSize = 20f
            setTextColor(Color.parseColor("#1B75D1"))
            background = btnBackground
            setPadding(50, 35, 50, 35)
            setOnClickListener { abrirCamara() }
        }

        imageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(800, 800).apply {
                topMargin = 70
            }
            background = GradientDrawable().apply {
                cornerRadius = 20f
                setColor(Color.parseColor("#EEEEEE"))
                setStroke(6, Color.DKGRAY)
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        layout.addView(btnAbrirCamara)
        layout.addView(imageView)
        setContentView(layout)
    }

    private fun abrirCamara() {
        val intentCamara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intentCamara.resolveActivity(packageManager) != null) {
            startActivityForResult(intentCamara, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                imageView.setImageBitmap(imageBitmap)
            }
        }
    }
}
