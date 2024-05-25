import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {
    companion object {
        const val MIN_CLICK_INTERVAL = 600
    }

    private var lastClickTime: Long = 0

    override fun onClick(v: View?) {
        val currentClickTime = System.currentTimeMillis()
        val elapsedTime = currentClickTime - lastClickTime
        lastClickTime = currentClickTime

        if (elapsedTime > MIN_CLICK_INTERVAL) {
            onSingleClick(v)
            // Animasyon ekleme
            val scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1f, 1.2f, 1f)
            val scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1f, 1.2f, 1f)
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY)
            animatorSet.duration = 200
            animatorSet.start()
        }
    }

    abstract fun onSingleClick(view: View?)
}
