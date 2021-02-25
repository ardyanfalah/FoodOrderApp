package e.ardya.foodorderapp.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleMenuOrderAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.databinding.FragmentDetailOrderBinding
import e.ardya.foodorderapp.databinding.FragmentPaymentBinding
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail_order.*
import kotlinx.android.synthetic.main.fragment_detail_order.view.*
import kotlinx.android.synthetic.main.fragment_payment.view.*

class PaymentFragment : BaseFragment(){
    private lateinit var homeViewModel: HomeViewModel
    private var adapter: RecyclerView.Adapter<RecycleMenuOrderAdapter.ViewHolder>? = null
    lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_payment,
            container,
            false
        )
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        setupClickListeners(view)
    }

    fun initObserve(){
        homeViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    private fun setupClickListeners(view: View) {
        view.setOnClickListener{

        }
        view.btn_payment_confirm.setOnClickListener{
            Log.d("test=>","masuk")
            homeViewModel.sendOrder(
            callbackSuccess = {
                Toast.makeText(context, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
                homeViewModel.listOrder.value?.clear()
                homeViewModel.listMenu.value?.clear()
                NavHostFragment.findNavController(this@PaymentFragment).navigate(R.id.action_paymentFragment_to_navigation_home)
            },
            callbackFailed = {
                Toast.makeText(context, "Pemesanan Gagal", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this@PaymentFragment).navigate(R.id.action_paymentFragment_to_navigation_home)
            })
        }
//        view.btn_add_count.setOnClickListener {
//            // TODO: Do some task here
//
//        }
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
    }


}