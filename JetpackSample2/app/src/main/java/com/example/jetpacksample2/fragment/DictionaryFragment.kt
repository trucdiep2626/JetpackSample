package com.example.jetpacksample2.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.jetpacksample2.databinding.FragmentDictionaryBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DictionaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DictionaryFragment : Fragment() ,WordAdapter.OnItemClickListener{
    override fun onClick(word: Word) {
       wordViewModel.selectWord(word)
        println(word.en)
    }

    lateinit var binding:FragmentDictionaryBinding
    lateinit var wordViewModel: WordViewModel
    lateinit var wordAdapter: WordAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     wordAdapter= WordAdapter(mutableListOf<Word>(),this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictionaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wordViewModel = activity?.let { ViewModelProvider(it) }!![WordViewModel::class.java]

        wordViewModel.getDictionary().observe(viewLifecycleOwner,   {

            wordAdapter= WordAdapter(it as MutableList<Word>,this)
            recyclerView.adapter = wordAdapter
        })
    }

    val onItemClick:(Word) ->Unit={
        println(it.en)
        wordViewModel.selectWord(it)
    }


    fun initView() {
        recyclerView  = binding.rvDictionary

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, VERTICAL)
        recyclerView.adapter= wordAdapter
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}