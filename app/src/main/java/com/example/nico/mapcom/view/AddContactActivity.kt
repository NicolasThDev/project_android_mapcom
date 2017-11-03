package com.example.nico.mapcom.view

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import com.example.nico.mapcom.R
import com.example.nico.mapcom.model.modelUtils.ContactManager
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class AddContactActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener {

    //-----------------
    //  CONSTANTS
    //-----------------

    companion object {
        val ADDRESS_AUTOCOMPLETE_REQUEST_CODE: Int = 1
    }

    //-----------------
    //  ATTRIBUTES
    //-----------------

    private var defaultColor: Int? = null;

    private var swActive: Switch? = null
    private var etFirstName: TextInputEditText? = null
    private var etLastName: TextInputEditText? = null
    private var etCompany: TextInputEditText? = null
    private var etPhoneNumber: TextInputEditText? = null
    private var etEmail: TextInputEditText? = null
    private var etAddress: TextInputEditText? = null
    private var etComments: TextInputEditText? = null
    private var btnAdd: Button? = null
    private var btnReset: Button? = null

    private fun findViews() {
        swActive = findViewById<View>(R.id.sw_active) as Switch
        etFirstName = findViewById<View>(R.id.et_firstName) as TextInputEditText
        etLastName = findViewById<View>(R.id.et_lastName) as TextInputEditText
        etCompany = findViewById<View>(R.id.et_company) as TextInputEditText
        etPhoneNumber = findViewById<View>(R.id.et_phoneNumber) as TextInputEditText
        etEmail = findViewById<View>(R.id.et_email) as TextInputEditText
        etAddress = findViewById<View>(R.id.et_address) as TextInputEditText
        etComments = findViewById<View>(R.id.et_comments) as TextInputEditText
        btnAdd = findViewById<View>(R.id.btn_add) as Button
        btnReset = findViewById<View>(R.id.btn_reset) as Button

        btnAdd!!.setOnClickListener(this)
        btnReset!!.setOnClickListener(this)

        etAddress!!.setOnFocusChangeListener(this)

        defaultColor = etAddress!!.solidColor
    }

    //-----------------
    //  FUNCTIONS
    //-----------------

    //--------------------------------------------------------------------
    //                      MAKE A TOAST
    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    //--------------------------------------------------------------------

    private fun resetFormAddContact() {
        swActive!!.isChecked = true
        etFirstName!!.setText("")
        etLastName!!.setText("")
        etCompany!!.setText("")
        etPhoneNumber!!.setText("")
        etEmail!!.setText("")
        etAddress!!.setText("")
        etComments!!.setText("")

        // focus on this field
        etFirstName!!.requestFocus()
    }

    private fun showAlertDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(R.string.alert_dialog_contact_add_message)
        alertDialogBuilder.setTitle(R.string.alert_dialog_contact_add_title)
        alertDialogBuilder.setPositiveButton(R.string.alert_dialog_btn_yes, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // click on btn YES
                // reset the form add contact
                resetFormAddContact()
            }
        })
        alertDialogBuilder.setNegativeButton(R.string.alert_dialog_btn_no, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                //click on btn NO
                // launch next activity
            }
        })
        alertDialogBuilder.show()
    }

    private fun callPlaceAutocompleteActivityIntent(requestCode: Int) {
        if (requestCode == ADDRESS_AUTOCOMPLETE_REQUEST_CODE) {
            // config filter type with address filter
            val typeFilter: AutocompleteFilter = AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                    .build()
            try {
                // config intent for autocomplete activity
                val intent: Intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                        .setFilter(typeFilter)
                        // set area for address research
                        .setBoundsBias(LatLngBounds(LatLng(41.27,-9.9),LatLng(51.0, -5.9)))
                        .build(this)
                startActivityForResult(intent, requestCode)
            } catch (e: GooglePlayServicesRepairableException) {
                // handle error
            }

        }
    }

    //-----------------
    //  OVERRIDES
    //-----------------

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            // focus on etAddress
            if (v!! == etAddress) {
                // onClick listener on etAddress when etAddress is focus
                etAddress!!.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        if (hasFocus) {
                            if (v!! == etAddress) {
                                // launch google places autocomplete
                                callPlaceAutocompleteActivityIntent(ADDRESS_AUTOCOMPLETE_REQUEST_CODE)
                            }
                        }
                    }
                })
                // launch google places autocomplete when etAddress is focus
                callPlaceAutocompleteActivityIntent(ADDRESS_AUTOCOMPLETE_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADDRESS_AUTOCOMPLETE_REQUEST_CODE) {
                // when user selected an address, replace etAddress value
                val place: Place = PlaceAutocomplete.getPlace(this, data)
                etAddress!!.setText(place.address)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        findViews()
    }

    override fun onClick(v: View) {
        if (v === btnAdd) {
            // Handle clicks for btnAdd
            // address is empty
            if (etAddress!!.text.isEmpty()) {
                etAddress!!.error = getString(R.string.error_invalid_address)
                etAddress!!.requestFocus()
                // postal code is empty
            } else {
                // add contact here
                ContactManager.addContact(
                        etFirstName!!.text.toString(),
                        etLastName!!.text.toString(),
                        etCompany!!.text.toString(),
                        etPhoneNumber!!.text.toString(),
                        etEmail!!.text.toString(),
                        etAddress!!.text.toString(),
                        etComments!!.text.toString(),
                        swActive!!.isChecked
                )
                // launch alert dialog
                showAlertDialog()
            }
        } else if (v === btnReset) {
            // Handle clicks for btnReset
            // clear all fields
            resetFormAddContact()
        }
    }
}