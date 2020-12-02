package com.mouhsinbourqaiba.jcdecaux.util

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["bikes", "stands"], requireAll = false)
fun availabilities(view: TextView, bikes: Int, stands: Int) {
    view.text = "Availabilities : $bikes Bikes , $stands Stands"
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["mechanicalBikes", "electricalBikes", "electricalInternalBatteryBikes","electricalRemovableBatteryBikes"], requireAll = false)
fun infoBikes(view: TextView, mechanicalBikes: Int, electricalBikes: Int,
              electricalInternalBatteryBikes: Int, electricalRemovableBatteryBikes:Int) {
    view.text = """
|               * Mechanical Bikes : $mechanicalBikes
|               * Electrical Bikes  : $electricalBikes
|               * Electrical internal battery Bikes  : $electricalInternalBatteryBikes
|               * Electrical removable battery Bikes  : $electricalRemovableBatteryBikes"""
        .trimMargin()
}

