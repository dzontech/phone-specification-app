package com.pma.specphone.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pma.specphone.R.layout
import com.pma.specphone.R.id
import androidx.fragment.app.Fragment
import com.pma.specphone.phonedetails.view.OldPhoneDetailsFragment
import com.pma.specphone.phonedetails.view.PhoneDetailsFragment
import com.pma.specphone.phonelist.AboutFragment

import com.pma.specphone.phonelist.EmailFragment
import com.pma.specphone.phonelist.HomePageFragment
import com.pma.specphone.phonelist.view.OldPhonesFragment

import com.pma.specphone.phonelist.view.PhoneListFragment

class MainActivity : AppCompatActivity(), ICoordinator{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        supportActionBar?.hide()

        showFragment(HomePageFragment(), true)

    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun showFragmentEmail() {
        showFragment(EmailFragment(), false)
    }

    override fun showFragmentAboutUs() {
        showFragment(AboutFragment(), false)
    }

    override fun showPhoneList() {
        showFragment(PhoneListFragment(), false)
    }

    override fun showDetailsFragment(phoneId: Int) {
        showFragment(PhoneDetailsFragment.newInstance(phoneId))
    }

    override fun showOldPhones() {
        showFragment(OldPhonesFragment(), false)
    }

    override fun showOldPhonesDetailsFragment(phoneId: Int) {
        showFragment(OldPhoneDetailsFragment.newInstance(phoneId))
    }
}

