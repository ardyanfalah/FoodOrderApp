package e.ardya.foodorderapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.utils.helper.SessionHelper
import java.lang.Exception

class RegistrationActivity : AppCompatActivity() {
    var activity: RegistrationActivity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        try {
            this.supportActionBar?.hide()
        } catch (e: Exception) {
            Log.d("Error ", e.toString())
        }
        checkLogin()
    }

    private fun checkLogin(){
        val email = SessionHelper["email", ""]
        if (email.isNotEmpty()) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

