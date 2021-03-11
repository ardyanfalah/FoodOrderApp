package e.ardya.foodorderapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecyclerAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.databinding.FragmenSeatBinding
import e.ardya.foodorderapp.databinding.FragmentSeatMainBinding
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragmen_seat.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_seat_main.view.*

class MainSeatFragment:BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentSeatMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_seat_main,
            container,
            false
        )
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        setupClickListeners(itemView)
        homeViewModel.listTempat.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                val viewMeja1: View = itemView.findViewById(R.id.v_meja_1)
                val viewMeja2: View = itemView.findViewById(R.id.v_meja_2)
                val viewMeja3: View = itemView.findViewById(R.id.v_meja_3)
                stateTable(viewMeja1,it[0].status_tmpt)
                stateTable(viewMeja2,it[1].status_tmpt)
                stateTable(viewMeja3,it[2].status_tmpt)
            }
        })
    }

    private fun stateTable(view:View, state: String){
        if(state == "Empty"){
            view.setBackgroundColor(resources.getColor(R.color.colorSeatEmpty))
        } else if(state == "Reserved"){
            view.setBackgroundColor(resources.getColor(R.color.colorSeatReserved))
        }
    }

    private fun setupClickListeners(view: View){

        view.v_meja_1.setOnClickListener {
            if(homeViewModel.listTempat.value!![0].status_tmpt == "Empty" ){
                if(homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    homeViewModel.addSeat(1)
                } else if(!homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    val seat:TransaksiModel.DetailTempat =  TransaksiModel.DetailTempat(
                        0,0,1
                    )
                    if(homeViewModel.listOrderTempat.value!!.contains(seat)){
                        homeViewModel.removeSeat(1)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatEmpty))
                    } else {
                        homeViewModel.addSeat(1)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    }
                }
            }
        }
        view.v_meja_2.setOnClickListener {
            if(homeViewModel.listTempat.value!![1].status_tmpt == "Empty" ){
                if(homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    homeViewModel.addSeat(2)
                } else if(!homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    val seat:TransaksiModel.DetailTempat =  TransaksiModel.DetailTempat(
                        0,0,2
                    )
                    if(homeViewModel.listOrderTempat.value!!.contains(seat)){
                        homeViewModel.removeSeat(2)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatEmpty))
                    } else {
                        homeViewModel.addSeat(2)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    }
                }
            }
        }
        view.v_meja_3.setOnClickListener {
            if(homeViewModel.listTempat.value!![2].status_tmpt == "Empty" ){
                if(homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    homeViewModel.addSeat(3)
                } else if(!homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    val seat:TransaksiModel.DetailTempat =  TransaksiModel.DetailTempat(
                        0,0,3
                    )
                    if(homeViewModel.listOrderTempat.value!!.contains(seat)){
                        homeViewModel.removeSeat(3)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatEmpty))
                    } else {
                        homeViewModel.addSeat(3)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    }
                }
            }
        }

    }

}