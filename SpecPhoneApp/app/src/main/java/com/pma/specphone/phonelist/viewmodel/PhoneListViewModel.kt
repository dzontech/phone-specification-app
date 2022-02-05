package com.pma.specphone.phonelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pma.specphone.base.data.IPhonesDataSource
import com.pma.specphone.base.functional.Either

import com.pma.specphone.base.functional.ICoroutineContextProvider
import com.pma.specphone.phonelist.view.PhoneListViewState
import com.pma.specphone.phonelist.view.PhoneListViewState.Processing
import com.pma.specphone.phonelist.view.PhoneListViewState.ErrorReceived
import com.pma.specphone.phonelist.view.PhoneListViewState.DataReceived

import kotlinx.coroutines.launch

class PhoneListViewModel (
    private val dataSource: IPhonesDataSource,
    private val coroutineContextProvider: ICoroutineContextProvider
    ) : ViewModel() {

        private val _state = MutableLiveData<PhoneListViewState>()
        val state: LiveData<PhoneListViewState>
        get() = _state

        fun getPhonesLatest() {
            viewModelScope.launch(coroutineContextProvider.io) {

                _state.postValue(Processing)


                    _state.postValue(

                        when (val result = dataSource.getPhonesLatest()) {
                            is Either.Success -> DataReceived(result.data)
                            is Either.Error -> ErrorReceived(result.exception.toString())

                        }


                    )

            }
        }
}