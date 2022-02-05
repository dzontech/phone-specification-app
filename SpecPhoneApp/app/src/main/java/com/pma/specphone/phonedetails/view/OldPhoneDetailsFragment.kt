package com.pma.specphone.phonedetails.view

import android.app.Activity
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.GenericTransitionOptions.with
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.with
import com.pma.specphone.R
import com.pma.specphone.base.data.ApiServiceProvider
import com.pma.specphone.base.data.PhonesDataSource
import com.pma.specphone.base.functional.ViewModelFactoryUtil
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.Processing
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.DataReceived
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.ErrorReceived
import com.pma.specphone.base.model.PhoneResponseItem
import com.pma.specphone.phonedetails.viewmodel.OldPhoneDetailsViewModel
import com.pma.specphone.phonedetails.viewmodel.PhoneDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_phone_details.*
import kotlinx.android.synthetic.main.item_phone.view.*

class OldPhoneDetailsFragment : Fragment() {

    private var phoneId: Int = -1
    private lateinit var viewModel: OldPhoneDetailsViewModel

    companion object {

        private const val PHONE_ID_KEY = "PHONE_ID"

        fun newInstance(phoneId: Int): OldPhoneDetailsFragment {

            return OldPhoneDetailsFragment().apply {
                arguments = Bundle().apply { putInt(PHONE_ID_KEY, phoneId) }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        phoneId = arguments?.getInt(PHONE_ID_KEY) ?: -1
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            OldPhoneDetailsViewModel(PhonesDataSource(ApiServiceProvider.phonesApiService))
        }).get(OldPhoneDetailsViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->

            phoneDetailsProgressBar.isVisible = state is Processing

            when (state) {
                is DataReceived -> setUpView(state.phone)
                is ErrorReceived -> showError(state.message)
            }
        })

        viewModel.getOldPhoneById(phoneId)

    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(phone: PhoneResponseItem) {

        Glide.with(requireContext()).load(phone.img_url).into(phoneDetailsImage)
        phoneDetailsName.text = phone.model
        phoneBrand.text = phone.brand
        phoneReleaseDate.text = phone.announced
        phoneDimension.text = phone.dimentions
        phoneOS.text = phone.oS
        phoneStorage.text = phone.internalMemory
        phoneBattery.text = phone.battery
        phoneCpu.text = phone.cPU
        phoneChipset.text = phone.chipset
        phoneColors.text = phone.colors
        phonePCamera.text = phone.primaryCamera
        phoneSCam.text = phone.secondaryCamera
        phoneRAM.text = phone.rAM
        phoneSIM.text = phone.sIM
        phoneWeight.text = phone.weightG
        phonePrice.text = phone.approxPriceEUR


    }
}