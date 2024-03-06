package com.example.echoreferral.ui.common.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.echoreferral.data.model.entities.Organisation

class TopNavBarViewModel:ViewModel() {

    val organisationStatus:MutableLiveData<Organisation?> = MutableLiveData()

    fun setOrganisation(organisation: Organisation?) {
        organisationStatus.value = organisation
    }

}