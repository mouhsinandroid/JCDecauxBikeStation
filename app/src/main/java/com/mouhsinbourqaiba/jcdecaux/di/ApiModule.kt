package com.mouhsinbourqaiba.jcdecaux.di

import com.mouhsinbourqaiba.jcdecaux.model.ApiBicycleStations
import com.mouhsinbourqaiba.jcdecaux.model.ApiServices
import com.mouhsinbourqaiba.jcdecaux.model.BicycleStationInterceptor
import com.mouhsinbourqaiba.jcdecaux.model.CacheInterceptor
import com.mouhsinbourqaiba.jcdecaux.util.Const.BASE_URL
import com.mouhsinbourqaiba.jcdecaux.util.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class ApiModule {



    @Provides
    fun provideBicycleStationsApi(): ApiBicycleStations {
        val cacheSize = 10 * 1024 * 1024 // 10MB
     //   val noConnectionInterceptor: NoConnectionInterceptor
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(BicycleStationInterceptor())
        builder.addNetworkInterceptor(CacheInterceptor())


        val client = builder.build()

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiBicycleStations::class.java)
    }


    @Provides
    open fun provideApiService(): ApiServices {
        return ApiServices()
    }
}