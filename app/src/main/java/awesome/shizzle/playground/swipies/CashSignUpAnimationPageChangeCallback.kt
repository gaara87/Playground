package awesome.shizzle.playground.swipies

import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

internal class CashSignUpAnimationPageChangeCallback(private val lottieView: LottieAnimationView) :
    ViewPager2.OnPageChangeCallback() {

    private var startFromBank = false

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        when (position) {
            0 -> when {
                positionOffset > 0 -> {
                    lottieView.cancelAnimation()
                    lottieView.setMinAndMaxFrame(81, 156)
                    lottieView.frame = 81 + (75 * positionOffset).toInt()
                }
                startFromBank -> {
                    lottieView.cancelAnimation()
                    lottieView.setMinAndMaxFrame(81, 156)
                    lottieView.frame = 81
                }
                else -> {
                    startFromBank = true
                    lottieView.setMinAndMaxFrame(0, 81)
                    lottieView.repeatCount = 0
                    lottieView.playAnimation()
                }
            }
            1 -> if (positionOffset > 0) {
                lottieView.cancelAnimation()
                lottieView.setMinAndMaxFrame(215, 274)
                lottieView.frame = 215 + (59 * positionOffset).toInt()
            } else {
                lottieView.setMinAndMaxFrame(156, 215)
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
            2 -> if (positionOffset > 0) {
                lottieView.cancelAnimation()
                lottieView.setMinAndMaxFrame(327, 373)
                lottieView.frame = 327 + (46 * positionOffset).toInt()
            } else {
                lottieView.setMinAndMaxFrame(274, 327)
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
            3 -> if (positionOffset > 0) {
                lottieView.cancelAnimation()
                lottieView.setMinAndMaxFrame(451, 497)
                lottieView.frame = 451 + (46 * positionOffset).toInt()
            } else {
                lottieView.setMinAndMaxFrame(373, 451)
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
            4 -> if (positionOffset > 0) {
                lottieView.cancelAnimation()
                lottieView.setMinAndMaxFrame(558, 603)
                lottieView.frame = 558 + (45 * positionOffset).toInt()
            } else {
                lottieView.setMinAndMaxFrame(497, 558)
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
            5 -> {
                lottieView.setMinAndMaxFrame(603, 682)
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()
            }
            else -> error("There shouldn't be a page here")
        }
    }
}
