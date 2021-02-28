package e.ardya.foodorderapp.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleOrderAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : BaseFragment(), RecycleOrderAdapter.Listener  {

    private lateinit var orderViewModel: OrderViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var horizontalLayoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecycleOrderAdapter.ViewHolder>? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        orderViewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        orderViewModel.getOrder()
        orderViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            recycler_view_personal_order.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                this@OrderFragment.adapter=activity?.baseContext?.let { it1 -> RecycleOrderAdapter(it1,it,this@OrderFragment) }
//                adapter = activity?.baseContext?.let { it1 -> RecyclerAdapter(it1,it,this@OrderFragment) }
                adapter = this@OrderFragment.adapter

            }
        })

    }



    fun initObserve(){
        orderViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }



    override fun onItemClick(menu: TransaksiModel.PemesananWithDetail) {
        orderViewModel.listMenu.value = menu.menu
        orderViewModel.listMenu.postValue(orderViewModel.listMenu.value)
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_dashboard_to_ratingFragment)
    }
}
