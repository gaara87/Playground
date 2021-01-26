package awesome.shizzle.playground.fragcomms

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface FragmentResult : Parcelable

@Parcelize
data class ParentFragmentResult(
    val message: String,
) : FragmentResult

@Parcelize
data class HodlFragmentResult(
    val messageFromHodl: String,
    val sendToActivity: Boolean,
) : FragmentResult

@Parcelize
data class DyorFragmentResult(
    val messageFromDyor: String,
    val sendToActivity: Boolean,
) : FragmentResult

