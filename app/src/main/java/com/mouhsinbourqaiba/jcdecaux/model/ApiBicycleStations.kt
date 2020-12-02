package com.mouhsinbourqaiba.jcdecaux.model

import com.mouhsinbourqaiba.jcdecaux.util.Const.GET_STATIONS
import io.reactivex.Single
import retrofit2.http.GET

interface ApiBicycleStations {

    @GET(GET_STATIONS)
    fun getBicycleStation(): Single<List<BicycleStation>>
}