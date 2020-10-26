package com.doc.fragmentcommunications.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doc.fragmentcommunications.FragmentCommunicator
import com.doc.fragmentcommunications.R
import kotlinx.android.synthetic.main.fragment_a.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {

    private lateinit var fragmentCommunicator: FragmentCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_a, container, false)
        fragmentCommunicator = activity as FragmentCommunicator

        var button = view.button
        button.setOnClickListener {
            var text:String = view.editTextTextPersonName.text.toString()
            fragmentCommunicator.passFragmentData(text)
        }

        return view
    }


}