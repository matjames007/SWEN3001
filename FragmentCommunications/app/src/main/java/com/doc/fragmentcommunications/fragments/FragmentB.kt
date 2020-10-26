package com.doc.fragmentcommunications.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doc.fragmentcommunications.FragmentCommunicator
import com.doc.fragmentcommunications.MainActivity
import com.doc.fragmentcommunications.R
import kotlinx.android.synthetic.main.fragment_b.view.*


class FragmentB : Fragment() {

    var displayMessage: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_b, container, false)
        displayMessage = arguments?.getString(MainActivity.MESSAGE_KEY)
        view.textView.text = displayMessage


        return view
    }


}