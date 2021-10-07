package com.example.jetpacksample2.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation)

//        binding.ivHeart.setOnClickListener{
//            binding.ivHeart.animate().apply {
//                duration=1000
//                rotationYBy(360f)
//            }
//        }

     //   val topToBottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
       // val blink = AnimationUtils.loadAnimation(this,R.anim.blink)
            //binding.ivHeart.startAnimation(topToBottom)
       // binding.ivHeart.startAnimation(blink)
    }
}