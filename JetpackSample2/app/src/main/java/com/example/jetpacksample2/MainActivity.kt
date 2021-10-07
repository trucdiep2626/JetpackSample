package com.example.jetpacksample2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpacksample2.animation.AnimationActivity
import com.example.jetpacksample2.databinding.ActivityMainBinding
import com.example.jetpacksample2.downloadManager.DownloadManagerActivity
import com.example.jetpacksample2.emoji.EmojiActivity
import com.example.jetpacksample2.fragment.ExampleFragmentActivity
import com.example.jetpacksample2.mediaPlayer.MediaPlayerActivity
import com.example.jetpacksample2.notification.NotificaionActivity
import com.example.jetpacksample2.palette.PaletteActivity
import com.example.jetpacksample2.sharing.SharingActivity
import com.example.jetpacksample2.slice.SliceActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.tvFragment.setOnClickListener {
            val intent = Intent(this@MainActivity, ExampleFragmentActivity::class.java)
            startActivity(intent)
        }

        binding.tvAnimation.setOnClickListener {
            val intent = Intent(this@MainActivity, AnimationActivity::class.java)
            startActivity(intent)
        }

        binding.tvPalette.setOnClickListener {
            val intent = Intent(this@MainActivity, PaletteActivity::class.java)
            startActivity(intent)
        }

        binding.tvEmoji.setOnClickListener {
            val intent = Intent(this@MainActivity, EmojiActivity::class.java)
            startActivity(intent)
        }

        binding.tvNotification.setOnClickListener {
            val intent = Intent(this@MainActivity, NotificaionActivity::class.java)
            startActivity(intent)
        }

        binding.tvDownloadManager.setOnClickListener {
            val intent = Intent(this@MainActivity, DownloadManagerActivity::class.java)
            startActivity(intent)
        }

        binding.tvMediaPlayer.setOnClickListener {
            val intent = Intent(this@MainActivity, MediaPlayerActivity::class.java)
            startActivity(intent)
        }

        binding.tvSlice.setOnClickListener {
            val intent = Intent(this@MainActivity, SliceActivity::class.java)
            startActivity(intent)
        }

        binding.tvSharing.setOnClickListener {
            val intent = Intent(this@MainActivity, SharingActivity::class.java)
            startActivity(intent)
        }
    }
}