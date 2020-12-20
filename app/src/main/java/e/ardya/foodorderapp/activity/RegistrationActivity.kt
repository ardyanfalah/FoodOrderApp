package e.ardya.foodorderapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import e.ardya.foodorderapp.R

class RegistrationActivity:AppCompatActivity() {
    var activity: RegistrationActivity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }
    fun toMain(view:View){
        var intent = Intent (activity, MainActivity::class.java)
        startActivity(intent)
    }
}