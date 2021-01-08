package e.ardya.foodorderapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import e.ardya.foodorderapp.R
import java.lang.Exception

class RegistrationActivity:AppCompatActivity() {
    var activity: RegistrationActivity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        try
        {
            this.supportActionBar?.hide();
        }catch (e: Exception){
            Log.d("Error ",e.toString())
        }
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }
    fun toMain(view:View){
        var intent = Intent (activity, MainActivity::class.java)
        startActivity(intent)
    }
}