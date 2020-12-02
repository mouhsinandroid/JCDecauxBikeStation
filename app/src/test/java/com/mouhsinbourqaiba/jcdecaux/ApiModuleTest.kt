package com.mouhsinbourqaiba.jcdecaux


import com.mouhsinbourqaiba.jcdecaux.di.ApiModule
import com.mouhsinbourqaiba.jcdecaux.model.ApiServices

class ApiModuleTest(private val mockServices: ApiServices): ApiModule() {

    override fun provideApiService(): ApiServices {
        return mockServices
    }

}