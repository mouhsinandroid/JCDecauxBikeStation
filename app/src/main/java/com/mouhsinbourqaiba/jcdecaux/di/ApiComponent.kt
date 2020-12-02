package com.mouhsinbourqaiba.jcdecaux.di

import com.mouhsinbourqaiba.jcdecaux.model.ApiServices
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun injectApi(service: ApiServices)
}