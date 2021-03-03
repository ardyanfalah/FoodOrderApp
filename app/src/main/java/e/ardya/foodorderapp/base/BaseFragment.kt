package e.ardya.foodorderapp.base

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import e.ardya.foodorderapp.view.DialogLoading

open class BaseFragment: Fragment() {
    private var mDialogLoading: DialogLoading? = null

    protected fun showMessage(title: String, message: String) {
        context?.let { ctx ->
            MaterialDialog(ctx)
                .title(text = title)
                .message(text = message)
                .positiveButton(text = "Ok", click = {
                    it.dismiss()
                })
                .show()
        }
    }

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