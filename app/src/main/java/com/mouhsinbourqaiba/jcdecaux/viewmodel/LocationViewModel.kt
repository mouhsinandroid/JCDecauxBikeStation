package com.mouhsinbourqaiba.jcdecaux.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mouhsinbourqaiba.jcdecaux.model.LocationLiveData

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData
}