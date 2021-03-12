package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.databinding.FragmentSeatMainBinding
import e.ardya.foodorderapp.databinding.FragmentSeatSecondaryBinding
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_seat_main.view.*
import kotlinx.android.synthetic.main.fragment_seat_secondary.view.*

class SecondarySeatFragment:BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentSeatSecondaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_seat_secondary,
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
                val viewMeja4: View = itemView.findViewById(R.id.v_meja_4)
                stateTable(viewMeja4,it[3].status_tmpt)
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

        view.v_meja_4.setOnClickListener {
            if(homeViewModel.listTempat.value!![3].status_tmpt == "Empty" ){
                if(homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    homeViewModel.addSeat(4)
                } else if(!homeViewModel.listOrderTempat.value.isNullOrEmpty()){
                    val seat: TransaksiModel.DetailTempat =  TransaksiModel.DetailTempat(
                        0,0,4
                    )
                    if(homeViewModel.listOrderTempat.value!!.contains(seat)){
                        homeViewModel.removeSeat(4)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatEmpty))
                    } else {
                        homeViewModel.addSeat(4)
                        it.setBackgroundColor(resources.getColor(R.color.colorSeatChosen))
                    }
                }
            }
        }

    }


}