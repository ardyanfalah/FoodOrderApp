package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        root.btnLogin.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_register_to_navigation_login2)
        }
        return root
    }
}