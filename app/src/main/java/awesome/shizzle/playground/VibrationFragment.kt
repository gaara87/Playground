package awesome.shizzle.playground

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_vibration.*

class VibrationFragment : Fragment(), Animator.AnimatorListener {

    private lateinit var vibrator: Vibrator
    private val vibrationPattern = VibrationEffect.createWaveform(
        TIMINGS,
        AMPLITUDES, -1
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vibration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lottie.addAnimatorListener(this)

        button.setOnClickListener {
            if (lottie.isAnimating) {
                lottie.cancelAnimation()
            }
            lottie.playAnimation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lottie.removeAnimatorListener(this)
    }

    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {
        vibrator.cancel()
    }

    override fun onAnimationCancel(animation: Animator?) {
        vibrator.cancel()
    }

    override fun onAnimationStart(animation: Animator?) {
        vibrator.vibrate(vibrationPattern)
    }

    companion object {
        private val effect = listOf(
            100L to 50,
            100L to 40,
            100L to 30,
            100L to 20,
            100L to 10,
            502L to 0, // big bubble starts bursting here 1s
            20L to 255,
            50L to 50,
            20L to 182,
            20L to 0,
            20L to 172,
            20L to 0,
            20L to 162,
            20L to 0,
            20L to 152,
            20L to 0,
            20L to 142,
            20L to 0,
            20L to 142,
            20L to 0,
            20L to 142,
            20L to 0,
            20L to 132,
            20L to 0,
            20L to 122,
            20L to 0,
            20L to 112,
            20L to 0,
            20L to 102,
            20L to 0
        )
        private val TIMINGS = effect.map { it.first }.toLongArray()
        private val AMPLITUDES = effect.map { it.second }.toIntArray()
    }

}
