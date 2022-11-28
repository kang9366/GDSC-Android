package com.sgkang.c82

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    lateinit var resultView: TextView
    lateinit var providerClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById<TextView>(R.id.resultView)

        val apiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(connectionCallBack)
            .addOnConnectionFailedListener(connectedFailCallback)
            .build()

        providerClient = LocationServices.getFusedLocationProviderClient(this)

        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            if(it){
                apiClient.connect()
            }else{
                Toast.makeText(this, "denined..", Toast.LENGTH_LONG).show()
            }
        }

        val status = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            apiClient.connect()
        }else{
            launcher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val connectionCallBack = object : GoogleApiClient.ConnectionCallbacks{
        override fun onConnected(p0: Bundle?) {
            providerClient.lastLocation.addOnSuccessListener {
                val latitude = it?.latitude
                val longitude = it?.longitude
                resultView.text = "$latitude, $longitude"
            }
        }

        override fun onConnectionSuspended(p0: Int) {
            //이용중인 provider가 이용할 수 없어지는 경우에 호출되는 함수
        }
    }

    val connectedFailCallback = object : GoogleApiClient.OnConnectionFailedListener{
        override fun onConnectionFailed(p0: ConnectionResult) {

        }
    }
}