package com.pma.specphone.phonelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.pma.specphone.R
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.about.*

class AboutFragment : Fragment() {


    private lateinit var contextActivity: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        contextActivity = container?.context!!
        return inflater.inflate(R.layout.about, container, false)
    }

}
