package com.pma.specphone.phonedetails.view

import com.pma.specphone.base.model.PhoneResponseItem

sealed class PhoneDetailsViewState {

    object Processing: PhoneDetailsViewState()
    data class DataReceived(val phone: PhoneResponseItem) : PhoneDetailsViewState()
    data class ErrorReceived(val message: String) : PhoneDetailsViewState()
}

