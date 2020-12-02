package com.mouhsinbourqaiba.jcdecaux.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mouhsinbourqaiba.jcdecaux.R
import com.mouhsinbourqaiba.jcdecaux.databinding.ItemBicyleStationBinding
import com.mouhsinbourqaiba.jcdecaux.model.BicycleStation

class ListBicycleStationAdapter(private val stationList: ArrayList<BicycleStation>) :
    RecyclerView.Adapter<ListBicycleStationAdapter.StationsViewHolder>(), StationClickListener {

    fun updateStationList(newStationList: List<BicycleStation>) {
        stationList.clear()
        stationList.addAll(newStationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBicyleStationBinding>(inflater, R.layout.item_bicyle_station, parent, false)

        return StationsViewHolder(binding)
    }

    override fun getItemCount() = stationList.size

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        holder.view.bicycleStation = stationList[position]
        holder.view.listener = this
        holder.view.btnDetails.setOnClickListener {
            val action = ListBicycleStationsFragmentDirections.actionDetail(stationList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onClick(v: View) {
        goToDetails(v)
    }

    private fun goToDetails(v: View) {
        for (bicycleStation in stationList) {
            if (v.tag == bicycleStation.name) {
                val action = ListBicycleStationsFragmentDirections.actionDetail(bicycleStation)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }


    class StationsViewHolder(var view: ItemBicyleStationBinding): RecyclerView.ViewHolder(view.root)



}