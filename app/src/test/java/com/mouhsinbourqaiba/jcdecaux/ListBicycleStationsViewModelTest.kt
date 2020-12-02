package com.mouhsinbourqaiba.jcdecaux

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mouhsinbourqaiba.jcdecaux.di.AppModule
import com.mouhsinbourqaiba.jcdecaux.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.jcdecaux.model.ApiServices
import com.mouhsinbourqaiba.jcdecaux.model.BicycleStation
import com.mouhsinbourqaiba.jcdecaux.viewmodel.ListBicycleStationsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListBicycleStationsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var services: ApiServices

    private val application = Mockito.mock(Application::class.java)

    private var viewModel = ListBicycleStationsViewModel(application, true)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder().appModule(AppModule(application))
            .apiModule(ApiModuleTest(services))
            .build()
            .injectViewBicycleStationsApi(viewModel)
    }

    @Test
    fun getBicycleStationsSuccess() {
        val bicycleStation = BicycleStation(9087, "marseille", "9087-MAZARGUES",
            "MAZARGUES - ROND POINT DE MAZARGUES (OBELISQUE)", null, null,
            null, null,
            null, null, null, null, null)

        val listBicycleStations = listOf(bicycleStation)

        val testSingle = Single.just(listBicycleStations)

        Mockito.`when`(services.getBicycleStations()).thenReturn(testSingle)

        viewModel.refreshData()

        Assert.assertEquals(1, viewModel.bicycleStations.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(false, viewModel.loadError.value)

    }

    @Test
    fun getLearnersFailure() {

        val testSingle = Single.error<List<BicycleStation>>(Throwable())

        Mockito.`when`(services.getBicycleStations()).thenReturn(testSingle)

        viewModel.refreshData()

        Assert.assertEquals(null, viewModel.bicycleStations.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(true, viewModel.loadError.value)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker({ it.run()}, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}