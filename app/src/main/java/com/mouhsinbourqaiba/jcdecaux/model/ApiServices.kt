package com.mouhsinbourqaiba.jcdecaux.model

import com.mouhsinbourqaiba.jcdecaux.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class ApiServices {

    @Inject
    lateinit var api: ApiBicycleStations

    init {
        DaggerApiComponent.create().injectApi(this)
    }

    fun getBicycleStations(): Single<List<BicycleStation>> {
        return api.getBicycleStation()
    }

}