package awesome.shizzle.playground.swipies

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import awesome.shizzle.playground.R

internal class CashAnimationTransformingPageChangeCallback(
    private val constraintLayout: ConstraintLayout,
    private val guideline: Guideline
) :
    ViewPager2.OnPageChangeCallback() {

    private val maxPercent =
        ResourcesCompat.getFloat(guideline.context.resources, R.dimen.shared_guide_percent)

    private val constraintSet = ConstraintSet().apply {
        clone(constraintLayout)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)

        if (position == 0) {
            when {
                positionOffset == 0.0f -> {
                    constraintSet.setGuidelinePercent(guideline.id, 1f)
                    constraintSet.applyTo(constraintLayout)
                }
                positionOffset > 0.0 && positionOffset <= 0.3 -> {
                    val guidePercent = 1 - ((positionOffset * (1 - maxPercent)) / 0.3)
                    constraintSet.setGuidelinePercent(guideline.id, guidePercent.toFloat())
                    constraintSet.applyTo(constraintLayout)
                }
                else -> {
                    constraintSet.setGuidelinePercent(guideline.id, maxPercent)
                    constraintSet.applyTo(constraintLayout)
                }
            }
        }
    }
}
