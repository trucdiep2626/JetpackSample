package com.example.jetpacksample2.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacksample2.R

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    var words: MutableList<Word>
    var listener:  OnItemClickListener


    constructor(words: MutableList<Word>, listener: OnItemClickListener) : super() {
        this.words = words
        this.listener = listener
    }

    fun updateWords(newWords: MutableList<Word>,  ) {
        words.clear()
        words.addAll(newWords)
        notifyDataSetChanged()
    }

   inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,

//        var en: TextView = itemView.findViewById( R.id.tvEn)
//        var vn: TextView = itemView.findViewById(R.id.tvVn)
//        var item :View= itemView.findViewById(R.id.wordItem)

        View.OnClickListener {
            var en: TextView = itemView.findViewById( R.id.tvEn)
            var vn: TextView = itemView.findViewById(R.id.tvVn)
            init {
                itemView.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                    listener.onClick(Word(en.text.toString(),vn.text.toString() ))

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        println(words.size)
        val word = words[position]
        holder.en.text = word.en
        holder.vn.text = word.vn


    }

    override fun getItemCount(): Int {
        return words.size
    }

    interface OnItemClickListener {
        fun onClick(word: Word)
    }
}