package e.ardya.foodorderapp.base

import androidx.fragment.app.Fragment
import e.ardya.foodorderapp.view.DialogLoading

open class BaseFragment: Fragment() {
    private var mDialogLoading: DialogLoading? = null

    protected fun showLoading() {
        mDialogLoading?.let {
            it.show()
            it.startAnimation()
        } ?: run {
            mDialogLoading = context?.let {
                DialogLoading(it)
            }
            mDialogLoading?.show()
        }
    }

    protected fun dismissLoading() {
        mDialogLoading?.dismiss()
    }
}