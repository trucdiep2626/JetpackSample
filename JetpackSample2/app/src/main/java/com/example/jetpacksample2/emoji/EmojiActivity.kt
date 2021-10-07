package com.example.jetpacksample2.emoji


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontRequest
import androidx.databinding.DataBindingUtil
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityEmojiBinding
import java.lang.ref.WeakReference

class EmojiActivity : AppCompatActivity() {
    val USE_BUNDLED_EMOJI = true
   lateinit var binding: ActivityEmojiBinding
    companion object {
        // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F4BB] (PERSONAL COMPUTER)
        private val WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB"

        // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F3A4] (MICROPHONE)
        private val WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4"

        @JvmField
        val EMOJI = WOMAN_TECHNOLOGIST + " " + WOMAN_SINGER
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_emoji)
        val config: EmojiCompat.Config
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = BundledEmojiCompatConfig(applicationContext)
        } else {
            // Use a downloadable font for EmojiCompat
            val fontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs)
            config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                .setReplaceAll(true)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                      //  Log.i(EmojiCompatApplication.TAG, "EmojiCompat initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                     //   Log.e(EmojiCompatApplication.TAG, "EmojiCompat initialization failed", throwable)
                    }
                })
        }
        EmojiCompat.init(config)
        // TextView variant provided by EmojiCompat library

        binding.emojiTextView.text = getString(R.string.emoji_text_view, EMOJI)

        // EditText variant provided by EmojiCompat library
        binding.emojiEditText.setText( getString(R.string.emoji_edit_text, EMOJI))

        // Button variant provided by EmojiCompat library
        binding.emojiButton.text = getString(R.string.emoji_button, EMOJI)

        // Regular TextView without EmojiCompat support; you have to manually process the text

        EmojiCompat.get().registerInitCallback(InitCallback(binding.regularTextView))

        // Custom TextView
        binding.emojiCustomTextView.text = getString(R.string.custom_text_view, EMOJI)
    }

    private class InitCallback(regularTextView: TextView) : EmojiCompat.InitCallback() {

        val regularTextViewRef = WeakReference(regularTextView)

        override fun onInitialized() {
            val regularTextView = regularTextViewRef.get()
            if (regularTextView != null) {
                val compat = EmojiCompat.get()
                val context = regularTextView.context
                regularTextView.text = compat.process(
                    context.getString(R.string.regular_text_view, EMOJI))
            }
        }

    }

}

