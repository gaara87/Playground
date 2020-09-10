package awesome.shizzle.playground.swipies

import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlin.math.absoluteValue

internal class PageTransformerPageChangeCallback(private val viewPager2: ViewPager2) :
    ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        when (position) {
            0 -> viewPager2.setPageTransformer { page, transformationPosition ->
                when (transformationPosition) {
                    in THRESHOLD..0.0f -> {
                        page.alpha = 1 - (transformationPosition / THRESHOLD)
                    }
                    in -1.0f..THRESHOLD -> {
                        page.alpha = 0f
                    }
                    else -> page.alpha = 1f
                }
            }
            else -> viewPager2.setPageTransformer(null)
        }
    }

    companion object {
        private const val THRESHOLD: Float = -0.1f
    }
}
