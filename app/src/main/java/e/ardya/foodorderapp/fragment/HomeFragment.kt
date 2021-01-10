package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleRecommenAdapter
import e.ardya.foodorderapp.adapter.RecyclerAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(),RecyclerAdapter.Listener {

    private lateinit var homeViewModel: HomeViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var horizontalLayoutManager:RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initObserve()
        homeViewModel.getMenu()
        homeViewModel.listMenu.observe(viewLifecycleOwner, Observer {
            Log.d("List Menu",it.toString())
            recycler_view.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = RecyclerAdapter(it,this@HomeFragment)
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

    override fun onItemClick(menu: MenuModel) {
        TODO("Not yet implemented")
    }
}
