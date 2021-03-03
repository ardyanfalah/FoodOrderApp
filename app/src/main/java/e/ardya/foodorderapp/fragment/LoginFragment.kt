package e.ardya.foodorderapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.databinding.FragmentLoginBinding
import e.ardya.foodorderapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register.view.btnRegis

class LoginFragment : BaseFragment() {

    lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_login,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        setupClickListeners(itemView)

    }

    private fun initObserve() {
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            showMessage("Info", it ?: "")
        })
        viewModel.actionGoToHome.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun setupClickListeners(view: View) {
        view.btnRegis.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_navigation_login_to_navigation_register2)
        }
    }

}