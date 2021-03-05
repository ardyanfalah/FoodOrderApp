package e.ardya.foodorderapp.fragment

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleOrderAdapter
import e.ardya.foodorderapp.adapter.RecycleRatingAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_detail_order.view.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_rating.*
import kotlinx.android.synthetic.main.fragment_rating.view.*

class RatingFragment: BaseFragment(), RecycleRatingAdapter.Listener {

    private lateinit var orderViewModel: OrderViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var horizontalLayoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecycleRatingAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        orderViewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        setupClickListeners(itemView)
        showActionBar()
        orderViewModel.getOrder()
        orderViewModel.listMenu.observe(viewLifecycleOwner, Observer {
            rv_rating.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                this@RatingFragment.adapter=activity?.baseContext?.let { it1 -> RecycleRatingAdapter(it1,it,this@RatingFragment) }
//                adapter = activity?.baseContext?.let { it1 -> RecyclerAdapter(it1,it,this@RatingFragment) }
                adapter = this@RatingFragment.adapter
                this@RatingFragment.adapter?.notifyDataSetChanged()

            }
        })

    }

    fun showActionBar(){
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.show()
        }
    }

    fun initObserve(){
        orderViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupClickListeners(view: View) {
        view.setOnClickListener{

        }
        view.btn_submit_rating.setOnClickListener {
            orderViewModel.sendRating(
                callbackSuccess = {
                    Toast.makeText(context, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
                    orderViewModel.listMenu.value?.clear()
                    NavHostFragment.findNavController(this@RatingFragment).navigate(R.id.action_ratingFragment_to_navigation_dashboard)
                },
                callbackFailed = {
                    Toast.makeText(context, "Pemesanan Gagal", Toast.LENGTH_SHORT).show();
                })

        }
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
    }

    override fun onItemClick(order: TransaksiModel.DetailMenuPemesanan) {
        Log.d("rating fragment=>","masuk")
    }

    override fun onRatingBarChange(
        rating: TransaksiModel.DetailMenuPemesanan,
        position: Int,
        value: Float
    ) {
        orderViewModel.listMenu.value?.get(position)?.rating = value.toInt()
//        orderViewModel.listMenu.postValue(orderViewModel.listMenu.value)
    }

    override fun onCatatanchange(
        rating: TransaksiModel.DetailMenuPemesanan,
        position: Int,
        value: Editable
    ) {
        Log.d("catatan =>",value.toString())
        orderViewModel.listMenu.value?.get(position)?.catatan = value.toString()

    }
}