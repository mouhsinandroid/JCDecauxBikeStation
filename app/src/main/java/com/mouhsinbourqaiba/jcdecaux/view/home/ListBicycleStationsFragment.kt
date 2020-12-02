package com.mouhsinbourqaiba.jcdecaux.view.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mouhsinbourqaiba.jcdecaux.R
import com.mouhsinbourqaiba.jcdecaux.model.BicycleStation
import com.mouhsinbourqaiba.jcdecaux.viewmodel.ListBicycleStationsViewModel
import kotlinx.android.synthetic.main.fragment_list_stations.*


class ListBicycleStationsFragment: Fragment() {

    private lateinit var viewModel: ListBicycleStationsViewModel
    private val listAdapter = ListBicycleStationAdapter(arrayListOf())


    private val stationsListDataObserver = Observer<List<BicycleStation>>{ list ->
        list?.let {
            stationsList.visibility = View.VISIBLE
            listAdapter.updateStationList(it)
        }

    }

    private val loadingLiveDataObserver = Observer<Boolean>{ isLoading ->

        loadingView.visibility = if(isLoading) {
            shimmerFrameLayout.startShimmerAnimation()
            shimmerFrameLayout.visibility = View.VISIBLE
            View.VISIBLE
        } else{
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            View.GONE
        }
        if(isLoading) {
            listError.visibility = View.GONE
            stationsList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean>{ isError ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_stations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProviders.of(this).get(ListBicycleStationsViewModel::class.java)
        viewModel.bicycleStations.observe(viewLifecycleOwner, stationsListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.refreshData()

        stationsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            stationsList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refreshData()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

}
