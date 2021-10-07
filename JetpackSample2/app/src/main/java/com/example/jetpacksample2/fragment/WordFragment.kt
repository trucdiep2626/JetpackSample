package com.example.jetpacksample2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.FragmentDictionaryBinding
import com.example.jetpacksample2.databinding.FragmentWordBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordFragment : Fragment() {
     lateinit var binding: FragmentWordBinding
     lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wordViewModel = activity?.let { ViewModelProvider(it) }!![WordViewModel::class.java]
        wordViewModel.getSelectedWordForDetail().observe(viewLifecycleOwner,{
            binding.tvEn.text=it.en
            binding.tvVn.text=it.vn
        })
    }
}