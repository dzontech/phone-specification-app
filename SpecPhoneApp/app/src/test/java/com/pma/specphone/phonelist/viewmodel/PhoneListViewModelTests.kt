package com.pma.specphone.phonelist.viewmodel

import androidx.lifecycle.Observer
import com.pma.specphone.base.InstantExecutorTest
import com.pma.specphone.base.TestCoroutineContextProvider
import com.pma.specphone.base.data.IPhonesDataSource
import com.pma.specphone.base.functional.Either
import com.pma.specphone.base.model.PhoneResponse
import com.pma.specphone.phonelist.view.PhoneListViewState
import com.pma.specphone.phonelist.view.PhoneListViewState.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class PhoneListViewModelTests : InstantExecutorTest() {

    @Mock
    lateinit var dataSource: IPhonesDataSource

    @Mock
    lateinit var stateObserver: Observer<PhoneListViewState>

    lateinit var viewModel: PhoneListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = PhoneListViewModel(dataSource, TestCoroutineContextProvider())
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `test getPhones, has result, state changed to Processing - DataReceived`() = runBlocking {

        val expectedResult = PhoneResponse()

        `when`(dataSource.getPhonesLatest()).thenReturn(Either.Success(expectedResult))

        viewModel.getPhonesLatest()

        verify(stateObserver).onChanged(Processing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `test getPhones, has error, state changed to Processing - ErrorReceived`() = runBlocking {

        val expectedError = Exception("test")

        `when`(dataSource.getPhonesLatest()).thenReturn(Either.Error(expectedError))

        viewModel.getPhonesLatest()

        verify(stateObserver).onChanged(Processing)
        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}