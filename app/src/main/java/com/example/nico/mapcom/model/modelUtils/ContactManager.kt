package com.example.nico.mapcom.model.modelUtils

import android.util.Log
import com.example.nico.mapcom.model.Contact
import com.example.nico.mapcom.model.ContactDao
import com.example.nico.mapcom.presenter.MyApplication
import com.google.android.gms.maps.model.LatLng

class ContactManager{

    //-------------------
    //     METHODS
    //-------------------

    companion object {
        // function to add a contact in mobile database
        fun addContact(
                firstName : String,
                lastName : String,
                society : String,
                phoneNumber : String,
                email : String,
                address: String,
                comment : String,
                active : Boolean,
                latLng: LatLng)
        {
            val contact = Contact()

            contact.firstName = firstName
            contact.lastName = lastName
            contact.society = society
            contact.phoneNumber = phoneNumber
            contact.email = email
            contact.address = address
            contact.comment = comment
            contact.active = active
            contact.latitude = latLng.latitude
            contact.longitude = latLng.longitude

            MyApplication.daoSession?.contactDao?.save(contact) // persist contact in database using GreenDAO
        }

        // function to remove a contact in mobile database
        fun removeContact(contact: Contact){
            MyApplication.daoSession?.contactDao?.delete(contact) // remove a contact in database using GreenDAO
        }

        // function to update a contact in mobile database
        fun updateContact(contact: Contact){
            MyApplication.daoSession?.contactDao?.update(contact) // update a contact in database using GreenDAO
        }

        // function to get all contact active
        fun getAllContactActive(): List<Contact>?{
            val aList = MyApplication.daoSession?.contactDao?.queryBuilder()?.where(ContactDao.Properties.Active.eq(1))?.list()
            Log.w("TAG", "list returned size = " + aList?.size)
            return aList
        }
    }

}