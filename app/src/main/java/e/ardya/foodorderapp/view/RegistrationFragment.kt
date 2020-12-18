package e.ardya.foodorderapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_registration.view.*

class RegistrationFragment:Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        root.btnRegister.setOnClickListener {
            val intent = Intent (activity, MainActivity::class.java)
            activity?.startActivity(intent)
        }
        return root

    }

}