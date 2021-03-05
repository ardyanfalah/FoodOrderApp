package e.ardya.foodorderapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.activity.RegistrationActivity
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.databinding.FragmentAccountBinding
import e.ardya.foodorderapp.utils.helper.SessionHelper
import e.ardya.foodorderapp.viewmodel.AccountViewModel
import e.ardya.foodorderapp.viewmodel.LoginViewModel

class AccountFragment : BaseFragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding:FragmentAccountBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_account,
            container,
            false
        )
        accountViewModel = ViewModelProvider(requireActivity()).get(AccountViewModel::class.java)
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        accountViewModel.showAccount()
        hideActionBar()
    }

    private fun initObserve() {
        accountViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
        accountViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            showMessage("Info", it ?: "")
        })
        accountViewModel.logoutObserve.observe(viewLifecycleOwner, Observer {
            SessionHelper.clearAll()
            val intent = Intent(activity, RegistrationActivity::class.java)
            startActivity(intent)
        })
    }

    fun hideActionBar(){
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.hide()
        }
    }

}
