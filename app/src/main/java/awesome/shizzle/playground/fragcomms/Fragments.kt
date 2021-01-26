package awesome.shizzle.playground.fragcomms

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult

fun Fragment.childFragmentResultListener(
    listener: (result: FragmentResult) -> Unit
) {
    childFragmentManager.setFragmentResultListener(
        FRAGMENT_RESULT_KEY,
        this
    ) { _, bundle ->
        listener(bundle.getParcelable(PARCELED_BUNDLE_KEY)!!)
    }
}

fun FragmentActivity.setFragmentResultListener(
    listener: (result: FragmentResult) -> Unit
) {
    supportFragmentManager.setFragmentResultListener(
        FRAGMENT_RESULT_KEY,
        this
    ) { _, bundle ->
        listener(bundle.getParcelable(PARCELED_BUNDLE_KEY)!!)
    }
}

fun Fragment.setFragmentResult(result: FragmentResult) {
    setFragmentResult(FRAGMENT_RESULT_KEY, bundleOf(PARCELED_BUNDLE_KEY to result))
}

private const val FRAGMENT_RESULT_KEY = "sharedResultKey"
private const val PARCELED_BUNDLE_KEY = "parceledResultData"
