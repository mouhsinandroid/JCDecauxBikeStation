package com.mouhsinbourqaiba.jcdecaux.di

import com.mouhsinbourqaiba.jcdecaux.viewmodel.ListBicycleStationsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ViewModelComponent {
    fun injectViewBicycleStationsApi(viewModel: ListBicycleStationsViewModel)
}