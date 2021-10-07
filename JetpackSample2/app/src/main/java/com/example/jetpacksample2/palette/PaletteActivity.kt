package com.example.jetpacksample2.palette

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityPaletteBinding


class PaletteActivity : AppCompatActivity(),Palette.PaletteAsyncListener {
    lateinit var binding: ActivityPaletteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_palette)
       val bitmap :  Bitmap = Bitmap.createBitmap(binding.imgTest.getDrawable().toBitmap())
        binding.btnChangeColor.setOnClickListener {
            Palette.from( bitmap).generate(  {
             onGenerated(it)
            });
        }

    }

    override fun onGenerated(p0: Palette?) {
        binding.tvTest.setBackgroundColor(p0!!.getVibrantColor(Color.BLUE))
    }

}