package e.ardya.foodorderapp.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import e.ardya.foodorderapp.R

class DialogLoading (context: Context) : Dialog(context) {
    private var progressText: TextView? = null
    private var ivLogo: ImageView? = null
    private var rotate: RotateAnimation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.view_dialog_loading)
        val cardView = findViewById<CardView>(R.id.card_progress)
        progressText = findViewById(R.id.progress_text)
        progressText?.visibility = View.GONE
        ivLogo = findViewById(R.id.iv_logo)
        cardView.preventCornerOverlap = true
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        startAnimation()
    }
    fun showLoading() {
        ivLogo?.startAnimation(rotate)
        this.show()
    }
    fun startAnimation() {
        rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate?.duration = 2000
        rotate?.repeatCount = Animation.INFINITE
        ivLogo?.startAnimation(rotate)
    }
}