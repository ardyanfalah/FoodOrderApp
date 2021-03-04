package e.ardya.foodorderapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.activity.RegistrationActivity
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.databinding.FragmentRegisterBinding
import e.ardya.foodorderapp.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment:BaseFragment() {

    lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_register,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            showMessage("Info", it ?: "")
        })
        viewModel.actionGotoLogin.observe(viewLifecycleOwner, Observer {
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_register_to_navigation_login2)
        })
    }

}