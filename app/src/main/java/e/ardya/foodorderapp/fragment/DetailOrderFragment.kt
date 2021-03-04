package e.ardya.foodorderapp.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail_order.*
import kotlinx.android.synthetic.main.fragment_detail_order.view.*

class DetailOrderFragment: BaseFragment(),RecycleMenuOrderAdapter.Listener {

    private lateinit var homeViewModel: HomeViewModel
    private var adapter: RecyclerView.Adapter<RecycleMenuOrderAdapter.ViewHolder>? = null
    lateinit var binding: FragmentDetailOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_detail_order,
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
//        setupBackButton()
        homeViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            recycler_view_order.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                this@DetailOrderFragment.adapter = activity?.baseContext?.let { it1 -> RecycleMenuOrderAdapter(it1,it,this@DetailOrderFragment) }
                adapter = this@DetailOrderFragment.adapter
                if(!it.isNullOrEmpty()){
                    val temp = it
                    var priceTotal:Int = 0
                    var tempHarga:Int = 0
                    var totalItem:Int = 0
                    temp.forEach { item ->
                        if(!item.harga_menu.isNullOrEmpty()){
                            tempHarga = item.harga_menu!!.toInt()*item.jumlah_pesan!!
                            priceTotal += tempHarga
                        }
                        if(item.jumlah_pesan != null){
                            totalItem += item.jumlah_pesan!!
                        }
                    }
                    homeViewModel.addTotalPrice(priceTotal.toString())
                    homeViewModel.addTotalItem(totalItem.toString())
                }

                this@DetailOrderFragment.adapter?.notifyDataSetChanged()

            }
        })
    }

    fun initObserve(){
        homeViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    private fun setupClickListeners(view: View) {
        view.setOnClickListener{

        }
        view.v_total_result.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_detailOrderFragment_to_paymentFragment)
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

    private fun setupBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity?)?.supportActionBar?.title = "Konfirmasi Pemesanan"

        }
    }


    override fun onItemClick(order: TransaksiModel.ItemTransaksi) {
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onAddOrder(order: TransaksiModel.ItemTransaksi, position: Int) {
        homeViewModel.addOrderDetail(order,position)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRemoveOrder(order: TransaksiModel.ItemTransaksi, position: Int) {
        homeViewModel.removeOrderDetail(order,position,callback = {
            NavHostFragment.findNavController(this@DetailOrderFragment).navigate(R.id.action_detailOrderFragment_to_navigation_home)
        })

    }
}