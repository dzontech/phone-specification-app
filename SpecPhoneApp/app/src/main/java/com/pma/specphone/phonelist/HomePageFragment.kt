package com.pma.specphone.phonelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.pma.specphone.R
import com.pma.specphone.base.ICoordinator
import kotlinx.android.synthetic.main.fragment_home_page.*


class HomePageFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPhoneList(contextActivity)
        showFragmentAboutUs(contextActivity)
        showFragmentEmail(contextActivity)
        showOldPhoneList(contextActivity)
    }

    private fun showPhoneList(contextActivity: Context) {
        home_page_btn_list.setOnClickListener{
            view -> Log.d("Prikazi", "Kliknuto")
            (activity as ICoordinator).showPhoneList()
        }
    }
    private fun showFragmentAboutUs(contextActivity: Context) {

        home_page_btn_about.setOnClickListener{
                view -> Log.d("Prikazi", "Kliknuto")
            (activity as ICoordinator).showFragmentAboutUs()
        }
    }
    private fun showFragmentEmail(contextActivity: Context) {
        home_page_btn_contact.setOnClickListener{
                view -> Log.d("Prikazi", "Kliknuto")
            (activity as ICoordinator).showFragmentEmail()
        }
    }

    private fun showOldPhoneList(contextActivity: Context) {
        home_page_btn_old.setOnClickListener{
                view -> Log.d("Prikazi", "Kliknuto")
            (activity as ICoordinator).showOldPhones()
        }
    }


}