package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleMenuOrderAdapter
import e.ardya.foodorderapp.adapter.RecyclerAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail_order.*
import kotlinx.android.synthetic.main.fragment_detail_order.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_order_detail.view.*

class DetailOrderFragment: BaseFragment(),RecycleMenuOrderAdapter.Listener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        setupClickListeners(view)
        homeViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            recycler_view_order.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = activity?.baseContext?.let { it1 -> RecycleMenuOrderAdapter(it1,it,this@DetailOrderFragment) }
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
            homeViewModel.sendOrder()
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

    override fun onItemClick(order: TransaksiModel.ItemTransaksi) {
    }

    override fun onAddOrder(order: TransaksiModel.ItemTransaksi, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onRemoveOrder(order: TransaksiModel.ItemTransaksi, position: Int) {
        TODO("Not yet implemented")
    }
}