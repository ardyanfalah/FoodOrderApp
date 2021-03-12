package e.ardya.foodorderapp.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.Formatter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.adapter.RecycleMenuOrderAdapter
import e.ardya.foodorderapp.base.BaseFragment
import e.ardya.foodorderapp.databinding.FragmentPaymentBinding
import e.ardya.foodorderapp.utils.helper.FileHelper
import e.ardya.foodorderapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_payment.view.*
import java.io.File

class PaymentFragment : BaseFragment(){

    private lateinit var homeViewModel: HomeViewModel
    private var adapter: RecyclerView.Adapter<RecycleMenuOrderAdapter.ViewHolder>? = null
    lateinit var binding: FragmentPaymentBinding
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_payment,
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
        showActionBar()
        setupClickListeners(view)
    }

    private fun initObserve(){
        homeViewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            if (it) showLoading() else dismissLoading()
        })
    }

    private fun showActionBar(){
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.show()
            (activity as AppCompatActivity?)?.supportActionBar?.title = "Kembali"
        }
    }

    private fun setupClickListeners(view: View) {
        view.setOnClickListener{

        }
        view.btn_payment_confirm.setOnClickListener{
            if(homeViewModel.mFilePath != null ){
                val fileSize =File(homeViewModel.mFilePath).length()
                if(fileSize < 1100000){
                    homeViewModel.sendOrder(
                        callbackSuccess = {
                            Toast.makeText(context, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
                            homeViewModel.listOrder.value?.clear()
                            homeViewModel.listMenu.value?.clear()
                            homeViewModel.listOrderTempat.value?.clear()
                            homeViewModel.mIsTakeout = "False"
                            homeViewModel.mFilePath = null
                            homeViewModel.mFileUri = null
                            homeViewModel.mFileName.value = null
                            homeViewModel.mFileName.postValue(homeViewModel.mFileName.value )
                            homeViewModel.mIsTakeout= "True"
                            homeViewModel.mIsThereEmptyPlace = null
                            NavHostFragment.findNavController(this@PaymentFragment).navigate(R.id.action_paymentFragment_to_navigation_home)
                        },
                        callbackFailed = {
                            Toast.makeText(context, "Pemesanan Gagal ", Toast.LENGTH_SHORT).show();
                            NavHostFragment.findNavController(this@PaymentFragment).navigate(R.id.action_paymentFragment_to_navigation_home)
                        })
                } else {
                    showMessage("Warning", "Size Bukti Pembayaran tidak boleh lebih dari 1 MB")
                }
            } else {
                showMessage("Warning", "Bukti Pembayaran tidak boleh kosong")
            }

        }
        view.btn_open_file.setOnClickListener {
            openGallery()
            Log.d("open gallery => ","clicked")
        }
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
    }

    private fun openGallery() {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//        intent.action = Intent.ACTION_GET_CONTENT;
        startActivityForResult(intent, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            homeViewModel.mFileUri = data?.data
            Log.d("activity result=>","masuk")
            if(homeViewModel.mFileUri != null){
                val path = activity?.contentResolver?.let { FileHelper.getImageRealPath(it,homeViewModel.mFileUri,null) }
                homeViewModel.mFilePath = path
                var fileSize = Formatter.formatShortFileSize(context,File(path).length())

                Toast.makeText(context, "File size = $fileSize", Toast.LENGTH_SHORT).show();
                homeViewModel.mFileName.value = File(path).name
                homeViewModel.mFileName.postValue(homeViewModel.mFileName.value)
            }
        }
    }

}