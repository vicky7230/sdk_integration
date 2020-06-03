package com.vicky7230.sdkintegration

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.moe.pushlibrary.MoEHelper
import com.moe.pushlibrary.models.GeoLocation
import com.moengage.core.Properties
import com.moengage.push.PushManager
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        //This should be done when token is refreshed
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.e(task.exception)
                return@OnCompleteListener
            }

            //Get new Instance ID token
            val token = task.result!!.token
            PushManager.getInstance().refreshToken(applicationContext, token)
        })
    }

    fun initializeUser(view: View) {
        //UNIQUE_ID is used to uniquely identify a user.
        MoEHelper.getInstance(applicationContext).setUniqueId("vicky7230")

        MoEHelper.getInstance(applicationContext).setFirstName("Vipin")
        MoEHelper.getInstance(applicationContext).setLastName("Kumar")
        MoEHelper.getInstance(applicationContext).setFullName("Vipin Kumar")
        MoEHelper.getInstance(applicationContext).setUserLocation(12.890931, 77.669990)
        MoEHelper.getInstance(applicationContext).setGender("Male")
        MoEHelper.getInstance(applicationContext).setNumber("123456")
        val date = Date()
        date.time = 1591081065
        MoEHelper.getInstance(applicationContext).setBirthDate(date)
        MoEHelper.getInstance(applicationContext).setEmail("vicky7230@gmail.com")
    }

    fun sendEvent(view: View) {
        val properties = Properties()
        properties.addAttribute("quantity", 2)
            .addAttribute("product", "iPhone")
            .addAttribute("purchaseDate", Date())
            .addAttribute("price", 5999.99)
            .addAttribute("userLocation", GeoLocation(40.77, 73.98))
        MoEHelper.getInstance(applicationContext).trackEvent("Purchase", properties)
    }
}