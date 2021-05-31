package com.homework.crudfan

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONException

class DataViewModel: ViewModel() {

    private val dataMahasiswa = MutableLiveData<ArrayList<DataMahasiswa>>()

    fun setData(context: Context) {

        val data = ArrayList<DataMahasiswa>()

        AndroidNetworking.initialize(context)
        //gunakan ip komputer kamu untuk mengakses file read_all.php
        AndroidNetworking.get("http://Ip_Komputer/crud_fan/read_all.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray?) {
                        Log.d("onResponse: ", response.toString())

                        try {
                            if (response != null) {
                                for (i in 0 until response.length()) {
                                    val dataResponse = response.getJSONObject(i)
                                    data.add(DataMahasiswa(dataResponse.getString("nim"),
                                            dataResponse.getString("nama"),
                                            dataResponse.getInt("umur"))
                                    )
                                }

                                dataMahasiswa.postValue(data)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("onError: ", anError.toString())
                    }
                })
    }

    fun getData(): LiveData<ArrayList<DataMahasiswa>> = dataMahasiswa
}