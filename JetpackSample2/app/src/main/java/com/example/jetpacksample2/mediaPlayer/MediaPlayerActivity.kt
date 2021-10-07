package com.example.jetpacksample2.mediaPlayer


import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityMediaPlayerBinding
import java.util.concurrent.TimeUnit


class MediaPlayerActivity : AppCompatActivity() {
    lateinit var binding : ActivityMediaPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setEnabled(true)
        binding.btnPause.setEnabled(false)
        binding.btnFastForward.setEnabled(false)
        binding.btnRewind.setEnabled(false)

        binding.seekBar.isClickable= false
        mediaPlayer = MediaPlayer.create(this, R.raw.test)

        mediaPlayer.setOnCompletionListener {
            binding.btnStart.setEnabled(true)
            binding.btnPause.setEnabled(false)
            binding.btnFastForward.setEnabled(false)
            binding.btnRewind.setEnabled(true)

        }

        mediaPlayer.setOnPreparedListener {
            if(mediaPlayer.isPlaying)
            {
                mediaPlayer.stop()
            }
            binding.btnStart.setEnabled(true)
            binding.btnPause.setEnabled(false)
            binding.btnFastForward.setEnabled(false)
            binding.btnRewind.setEnabled(false)

        }

        binding.btnRewind.setOnClickListener {
            var currentPosition: Int = mediaPlayer.currentPosition
            var subtractTime=5000
            if(currentPosition - subtractTime >0)
            {
                mediaPlayer.seekTo(currentPosition - subtractTime)
            }
            binding.btnFastForward.setEnabled(true)
        }

        binding.btnFastForward.setOnClickListener {
            var duration:Int = mediaPlayer.duration
            var currentPosition: Int = mediaPlayer.currentPosition
            var addTime=5000
            if(currentPosition + addTime < duration)
            {
                mediaPlayer.seekTo(currentPosition + addTime)
            }
        }

        binding.btnPause.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                binding.btnStart.isEnabled = true
                binding.btnPause.isEnabled = false
                Toast.makeText(this,"media pause",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnStart.setOnClickListener {
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause= false
                Toast.makeText(this,"media playing",Toast.LENGTH_SHORT).show()
            }else{
               var duration:Int = mediaPlayer.duration

                var currentPosition: Int = mediaPlayer.currentPosition
                binding.tvMaxTime.text = millisecondsToString(duration)
                binding.seekBar.max = duration
                if(currentPosition==0)
                {
                    binding.seekBar.max = duration

                    println(millisecondsToString(duration))
                }
                else if (currentPosition == duration)
                {
                    mediaPlayer.reset()
                }


            mediaPlayer.start()
                Toast.makeText(this,"media playing",Toast.LENGTH_SHORT).show()
        }
            initializeSeekBar()
            binding.btnStart.isEnabled = false
            binding.btnPause.isEnabled = true
            binding.btnFastForward.isEnabled=true
            binding.btnRewind.isEnabled= true

            mediaPlayer.setOnCompletionListener {
                binding.btnStart.isEnabled = true
                binding.btnPause.isEnabled  = false
                Toast.makeText(this,"end",Toast.LENGTH_SHORT).show()
            }


        }


    }

    fun initializeSeekBar() {

        runnable = Runnable {
            binding.tvCurrentPosition.text = millisecondsToString( mediaPlayer.currentPosition)
            binding.seekBar.progress = mediaPlayer.currentPosition

            handler.postDelayed(runnable, 500)
        }
        handler.postDelayed(runnable, 500)
    }



    fun millisecondsToString(milliseconds: Int) : String  {
        val minutes: Long =  TimeUnit.MILLISECONDS.toMinutes(milliseconds.toLong())
        val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(milliseconds.toLong())
        return "$minutes:$seconds"
    }
}