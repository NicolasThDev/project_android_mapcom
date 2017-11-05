package com.example.nico.mapcom.model.modelUtils

import android.location.Location
import com.example.nico.mapcom.model.Contact
import com.example.nico.mapcom.view.MapsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapUtils{
    companion object {
        fun displayContactMarker(listContact : List<Contact>, googleMap : GoogleMap, myLocation : Location?, activity: MapsActivity) : ArrayList<Marker>{
            // remove all markers
            googleMap.clear()

            val aListMarker : ArrayList<Marker> = ArrayList()
            val latLngBounds : LatLngBounds.Builder = LatLngBounds.Builder()


            for (contact : Contact in listContact){
                //add contact position in zoom area
                val latLng = LatLng(contact.latitude, contact.longitude)
                latLngBounds.include(latLng)

                // add contact marker on map
                val marker : Marker = googleMap.addMarker(MarkerOptions().position(latLng).title(contact.society))
                marker.tag = contact

                // add marker in returned marker list
                aListMarker.add(marker)
            }

            // myLocation is available, include it in the zoom area
            if (myLocation != null){
                latLngBounds.include(LatLng(myLocation.latitude, myLocation.longitude))
            }

            // animate camera with param with height and padding
            val width = activity.resources.displayMetrics.widthPixels
            val height = activity.resources.displayMetrics.heightPixels
            val padding = 400
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), width, height, padding))

            return aListMarker
        }
    }
}