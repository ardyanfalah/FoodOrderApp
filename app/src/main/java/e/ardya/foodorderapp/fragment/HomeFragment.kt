package e.ardya.foodorderapp.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleRecommenAdapter
import e.ardya.foodorderapp.adapter.RecyclerAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_menu.*


class HomeFragment : BaseFragment(),RecyclerAdapter.Listener {

    private lateinit var homeViewModel: HomeViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var horizontalLayoutManager:RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var  dialog:CountOrderDialogFragment = CountOrderDialogFragment()
    var orders:ArrayList<MenuModel.Data> = ArrayList()
    var totalPrice: Int= 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        if(homeViewModel.listMenu.value.isNullOrEmpty()){
            homeViewModel.getMenu()
        }
        if (dialog.dialog == null && homeViewModel.getOrderSize() >0){
            dialog.show(childFragmentManager,CountOrderDialogFragment.TAG)
        }
        homeViewModel.listMenu.observe(viewLifecycleOwner, Observer {
            recycler_view.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                this@HomeFragment.adapter=activity?.baseContext?.let { it1 -> RecyclerAdapter(it1,it,this@HomeFragment) }
//                adapter = activity?.baseContext?.let { it1 -> RecyclerAdapter(it1,it,this@HomeFragment) }
                adapter = this@HomeFragment.adapter
                this@HomeFragment.adapter?.notifyDataSetChanged()

            }
        })
        homeViewModel.listOrder.observe(viewLifecycleOwner, Observer {
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
                if (dialog.dialog == null){
                    dialog.show(childFragmentManager,CountOrderDialogFragment.TAG)
                }
            }
        })

        recycler_view_recommend.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            // set the custom adapter to the RecyclerView
            adapter = RecycleRecommenAdapter()
        }
//        recycler_view.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = RecyclerAdapter(vie)
//        }
    }



    fun initObserve(){
        homeViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    override fun onItemClick(menu: MenuModel.Data) {

    }

    override fun onOrder(menu: MenuModel.Data, position: Int) {
        Log.d("Menu Clicked",menu.toString())

        if(dialog.dialog == null){
//            this.fragmentManager?.let { dialog.show(it,CountOrderDialogFragment.TAG) }
            dialog.show(childFragmentManager,CountOrderDialogFragment.TAG)

            homeViewModel.addOrder(menu,position)
        } else {
            homeViewModel.addOrder(menu,position)
        }

    }

    override fun onAddOrder(menu: MenuModel.Data, position: Int) {
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRemoveOrder(menu: MenuModel.Data, position: Int) {
        homeViewModel.removeOrder(menu,position,callback = {
            dialog.dialog?.dismiss()
        })

    }

}
