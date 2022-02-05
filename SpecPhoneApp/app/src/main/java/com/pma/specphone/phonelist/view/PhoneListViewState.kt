package com.pma.specphone.phonelist.view
import com.pma.specphone.base.model.PhoneResponseItem

sealed class PhoneListViewState {

    object Processing: PhoneListViewState()
    data class DataReceived(val phones: List<PhoneResponseItem>) : PhoneListViewState()
    data class ErrorReceived(val message: String) : PhoneListViewState()
}