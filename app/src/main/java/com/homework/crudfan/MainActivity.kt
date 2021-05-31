package com.homework.crudfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.homework.crudfan.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AndroidNetworking.initialize(applicationContext)

        binding.btnAdd.setOnClickListener(this)
        binding.btnViewData.setOnClickListener(this)
    }

    private fun addData(nim:String, nama:String, umur:String) {

        //gunakan ip komputer kamu untuk mengakses file create.php
        AndroidNetworking.post("http://Ip_Komputer/crud_fan/create.php")
            .addBodyParameter("nim", nim)
            .addBodyParameter("nama", nama)
            .addBodyParameter("umur", umur)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("onResponse: ", response.toString())
                    Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
                }

                override fun onError(anError: ANError) {
                    Log.d("onError: ", anError.toString())
                    Toast.makeText(applicationContext, "cannot be saved", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_add -> {
                val getNim = binding.inputNim.text.toString().trim()
                val getNama = binding.inputNama.text.toString()
                val getUmur = binding.inputUmur.text.toString().trim()

                if (getNim == "" || getNama == "" || getUmur == "") {
                    Toast.makeText(this, resources.getString(R.string.input_message), Toast.LENGTH_SHORT).show()
                } else {
                    addData(getNim, getNama, getUmur)
                    binding.inputNim.setText("")
                    binding.inputNama.setText("")
                    binding.inputUmur.setText("")
                }
            }

            R.id.btn_view_data -> {
                val intent = Intent(this@MainActivity, ListData::class.java)
                startActivity(intent)
            }
        }
    }
}