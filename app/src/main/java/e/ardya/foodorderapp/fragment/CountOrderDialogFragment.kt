package e.ardya.foodorderapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.databinding.FragmentDialogOrderCountBinding
import e.ardya.foodorderapp.viewmodel.HomeViewModel


class CountOrderDialogFragment : DialogFragment() {

    companion object {

        const val TAG = "CountOrderDialogFragment"
        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): CountOrderDialogFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = CountOrderDialogFragment()
            fragment.arguments = args
            fragment.showsDialog
            return fragment
        }
    }

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: FragmentDialogOrderCountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_dialog_order_count,
            container,
            false
        )
        viewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
        setupClickListeners(view)

        val window: Window? = dialog!!.window
        if(window != null){
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setGravity(Gravity.BOTTOM)
            val params = window.attributes
            params.y = 150
            params.x = 100
            window.attributes=params
        }
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setPriceTotal(){

    }

    private fun setupView(view: View) {
//        view.tvTitle.text = arguments?.getString(KEY_TITLE)
//        view.tvSubTitle.text = arguments?.gretString(KEY_SUBTITLE)
    }

    private fun setupClickListeners(view: View) {
        view.setOnClickListener{
            dismiss()
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_detailOrderFragment)

        }
//        view.btnPositive.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
    }


}