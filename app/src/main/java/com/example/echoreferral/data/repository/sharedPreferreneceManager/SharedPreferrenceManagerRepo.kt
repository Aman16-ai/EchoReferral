package com.example.echoreferral.data.repository.sharedPreferreneceManager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SharedPreferrenceManagerRepo(context: Context) : SharedPreferrenceManagerRepoI {

    private val key : String? = "token_key"
    private val sharedPreference : SharedPreferences
    private val editor:SharedPreferences.Editor

    init {
        sharedPreference = context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }

    var token
        get() = sharedPreference.getString(key,"").toString()
        set(value) {
            editor.putString(key,value)
            editor.commit()
        }

    fun deleteToken() {
        editor.remove(key).apply()
    }
    private var _auth_token = MutableLiveData<String?>()
    override val auth_token:LiveData<String?>
        get() = _auth_token

    override fun getToken() {
        val token = sharedPreference.getString("auth_token",null)
        _auth_token.postValue(token)
    }

    override fun saveToken(token:String?) {
        with(sharedPreference.edit()) {
            putString("auth_token",token)
        }
    }


}