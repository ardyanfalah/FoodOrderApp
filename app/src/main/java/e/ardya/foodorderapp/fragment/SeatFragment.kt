package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecycleMenuOrderAdapter
import e.ardya.foodorderapp.adapter.ViewPagerSeatAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.databinding.FragmenSeatBinding
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragmen_seat.view.*

class SeatFragment: BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmenSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragmen_seat,
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
        setupClickListener(view)
        val viewPager:ViewPager = view.findViewById(R.id.vp_seat)
        setupViewPager(viewPager)
        homeViewModel.getTempat()
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter: ViewPagerSeatAdapter = ViewPagerSeatAdapter(this.childFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(SecondarySeatFragment(), "Secondary")
        adapter.addFragment(MainSeatFragment(), "Main")
        // setting adapter to view pager.
        viewpager.adapter = adapter
        viewpager.arrowScroll(View.FOCUS_RIGHT)
    }

    private fun initObserve(){
        homeViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    fun setupClickListener(view:View){

        view.btn_choose_Seat.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_seatFragment_to_detailOrderFragment)
        }
    }

}