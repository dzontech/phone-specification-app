package com.pma.specphone.phonelist.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.pma.specphone.R
import com.pma.specphone.base.ICoordinator
import com.pma.specphone.base.data.ApiServiceProvider
import com.pma.specphone.base.data.PhonesDataSource
import com.pma.specphone.base.functional.CoroutineContextProvider
import com.pma.specphone.base.functional.ViewModelFactoryUtil
import com.pma.specphone.base.model.PhoneResponseItem
import com.pma.specphone.phonelist.recycler.PhonesRVAdapter
import com.pma.specphone.phonelist.viewmodel.PhoneListViewModel
import com.pma.specphone.phonelist.view.PhoneListViewState
import com.pma.specphone.phonelist.view.PhoneListViewState.Processing
import com.pma.specphone.phonelist.view.PhoneListViewState.ErrorReceived
import com.pma.specphone.phonelist.view.PhoneListViewState.DataReceived
import kotlinx.android.synthetic.main.fragment_phone_list.*
import androidx.appcompat.app.AppCompatActivity

open class PhoneListFragment : Fragment() {
    lateinit var viewModel: PhoneListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            PhoneListViewModel(
                PhonesDataSource(ApiServiceProvider.phonesApiService),
                CoroutineContextProvider()
            )
        }).get(PhoneListViewModel::class.java)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getPhonesLatest()
    }
    private fun bindFromViewModel() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            phoneListProgressBar.isVisible = state is Processing

            when (state) {
                is DataReceived -> { setUpRecyclerView(state.phones) }
                is ErrorReceived -> showError(state.message)
            }
        }
    }
    private fun setUpRecyclerView(phones: List<PhoneResponseItem>) {
       phoneListRV.adapter = PhonesRVAdapter(phones) { phoneId ->
            (activity as ICoordinator).showDetailsFragment(phoneId)

            Log.d("PhoneListFragment", "$phoneId")
        }

    }
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}