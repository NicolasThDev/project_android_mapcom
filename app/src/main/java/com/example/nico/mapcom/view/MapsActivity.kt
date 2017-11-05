package com.example.nico.mapcom.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.nico.mapcom.DetailContactActivity
import com.example.nico.mapcom.R
import com.example.nico.mapcom.model.Contact
import com.example.nico.mapcom.model.modelUtils.Constants
import com.example.nico.mapcom.model.modelUtils.ContactManager
import com.example.nico.mapcom.model.modelUtils.MapUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private var mMap: GoogleMap? = null
    private var listContact : List<Contact>? = null
    private var myLocation : Location? = null

    private fun displayContactMarker(){
        if(listContact != null && !listContact!!.isEmpty()){
            // display list of marker on map
            MapUtils.displayContactMarker(listContact!!, mMap!!, myLocation, this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onResume() {
        // on resume activity, get all contacts active in DB and display them on map
        listContact = ContactManager.getAllContactActive()
        if (mMap != null){
            displayContactMarker()
        }
        super.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // display contact list on map
        displayContactMarker()
        mMap?.setInfoWindowAdapter(this)
        mMap?.setOnInfoWindowClickListener (this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // create menu item for AddContactActivity with icon
        val addContactIcon = getDrawable(R.drawable.ic_person_add_black_24dp)
        addContactIcon.setColorFilter(resources.getColor(R.color.white, this.theme), PorterDuff.Mode.SRC_IN)
        menu?.add(0,Constants.MENU_ID_ADD_CONTACT,0,R.string.add_contact)?.setIcon(addContactIcon)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            // if menu item addContact selected, launch AddContactActivity
            Constants.MENU_ID_ADD_CONTACT -> {
                intent = Intent(this, AddContactActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("InflateParams")
    override fun getInfoContents(p0: Marker?): View? {
        // Custom the marker layout here
        val view : View = LayoutInflater.from(this).inflate(R.layout.custom_layout_contact_marker, null)
        val tvCompany = view.findViewById<View>(R.id.tv_company) as TextView
        val ivIconDirection = view.findViewById<View>(R.id.iv_icon_direction) as ImageView

        val contact = p0?.tag as Contact?

        // change color of direction icon
        ivIconDirection.setColorFilter(resources.getColor(R.color.colorBlueGoogle, this.theme), PorterDuff.Mode.SRC_IN)

        // set company text with the name of the company
        tvCompany.text = contact?.society
        return view
    }

    // call getInfoContents() if this function return null
    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }

    // when click on marker info window, launch DetailContactActivity
    override fun onInfoWindowClick(p0: Marker?) {
        val contact = p0?.tag as Contact

        val intent = Intent(this, DetailContactActivity::class.java)

        // add CONTACT_ID in the intent
        intent.putExtra("CONTACT_ID", contact.id)

        startActivity(intent)
    }
}
