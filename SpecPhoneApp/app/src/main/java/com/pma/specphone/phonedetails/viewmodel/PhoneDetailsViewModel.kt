package com.pma.specphone.phonedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pma.specphone.base.data.PhonesDataSource
import com.pma.specphone.base.functional.Either
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.DataReceived
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.ErrorReceived
import com.pma.specphone.phonedetails.view.PhoneDetailsViewState.Processing
import kotlinx.coroutines.launch

class PhoneDetailsViewModel(private val dataSource: PhonesDataSource) : ViewModel() {

    private val _state = MutableLiveData<PhoneDetailsViewState>()
    val state: LiveData<PhoneDetailsViewState>
        get() = _state

         fun getPhoneById(id: Int) {
             viewModelScope.launch {

                 _state.postValue(Processing)

                 _state.postValue(
                     when (val result = dataSource.getPhoneById(id)) {
                         is Either.Success -> DataReceived(result.data)
                         is Either.Error -> ErrorReceived(result.exception.toString())
                     }
                 )
             }

         }
}


