package com.example.jetpacksample2.emoji

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.emoji.widget.EmojiTextViewHelper

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mEmojiTextViewHelper: EmojiTextViewHelper? = null

    /**
     * Returns the [EmojiTextViewHelper] for this TextView.
     *
     * This property can be called from super constructors through [#setFilters] or [#setAllCaps].
     */
    private val emojiTextViewHelper: EmojiTextViewHelper
        get() {
            if (mEmojiTextViewHelper == null) {
                mEmojiTextViewHelper = EmojiTextViewHelper(this)
            }
            return mEmojiTextViewHelper as EmojiTextViewHelper
        }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }

    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }

}
