package e.ardya.foodorderapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.activity.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_register.view.*

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        root.btnLogin.setOnClickListener {
            val registrationActivity = RegistrationActivity()
            var intent = Intent (activity, MainActivity::class.java)
            startActivity(intent)
        }
        root.btnRegis.setOnClickListener {

        }
        return root
    }
}