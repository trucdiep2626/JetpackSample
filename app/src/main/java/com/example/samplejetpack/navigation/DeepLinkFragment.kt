package com.example.samplejetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.FragmentDeepLinkBinding
import com.example.samplejetpack.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeepLinkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeepLinkFragment : Fragment() {
    lateinit var binding:FragmentDeepLinkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeepLinkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arg= arguments?.getString("arg")
        binding.tvArg.text=arg

//
//        val pendingIntent = findNavController().createDeepLink()
//            .setGraph(R.navigation.nav_graph)
//            .setDestination(R.id.deepLinkFragment)
//            .setArguments()
    }

}