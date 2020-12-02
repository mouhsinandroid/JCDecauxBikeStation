package com.mouhsinbourqaiba.jcdecaux.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mouhsinbourqaiba.jcdecaux.R
import com.mouhsinbourqaiba.jcdecaux.databinding.FragmentBicycleStationDetailBinding
import com.mouhsinbourqaiba.jcdecaux.model.BicycleStation

class BicycleStationDetailFragment: Fragment() {
    private var station: BicycleStation? = null
    private lateinit var dataBinding: FragmentBicycleStationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bicycle_station_detail,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            station = BicycleStationDetailFragmentArgs.fromBundle(it).bicycleStation
        }

        dataBinding.bicycleStation = station

    }
}