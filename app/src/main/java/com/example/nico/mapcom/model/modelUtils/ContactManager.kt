package com.example.nico.mapcom.model.modelUtils

import com.example.nico.mapcom.model.Contact
import com.example.nico.mapcom.presenter.MyApplication

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
                active : Boolean)
        {
            val contact : Contact = Contact()

            contact.firstName = firstName
            contact.lastName = lastName
            contact.society = society
            contact.phoneNumber = phoneNumber
            contact.email = email
            contact.address = address
            contact.comment = comment
            contact.active = active

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
    }

}