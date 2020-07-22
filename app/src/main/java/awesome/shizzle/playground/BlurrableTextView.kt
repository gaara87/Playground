package awesome.shizzle.playground

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Region
import android.util.AttributeSet
import android.view.View
import com.robinhood.ticker.TickerView

class BlurrableTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TickerView(context, attrs, defStyleAttr) {
    private val blurFilter = BlurMaskFilter(textSize / 3, BlurMaskFilter.Blur.NORMAL)
    private var isBlurred = false

    init {
        setOnClickListener {
            if (isBlurred) {
                setLayerType(View.LAYER_TYPE_NONE, textPaint)
                textPaint.maskFilter = null
            } else {
                setLayerType(View.LAYER_TYPE_SOFTWARE, textPaint)
                textPaint.maskFilter = blurFilter
            }
            isBlurred = !isBlurred
        }
    }
}