package com.pma.specphone.phonelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pma.specphone.R
import com.pma.specphone.base.ICoordinator
import kotlinx.android.synthetic.main.email_fragment.*
import kotlinx.android.synthetic.main.fragment_home_page.*


class EmailFragment : Fragment(){




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

        return inflater.inflate(R.layout.email_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email_window_button.setOnClickListener{

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/html"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("govedaricajovan99@gmail.com"))

            startActivity(intent)
        }

    }




}