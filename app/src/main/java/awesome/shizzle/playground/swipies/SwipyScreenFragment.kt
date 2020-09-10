package awesome.shizzle.playground.swipies

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import awesome.shizzle.playground.R
import awesome.shizzle.playground.databinding.IncludePageBinding
import kotlinx.android.parcel.Parcelize

class SwipyScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            IncludePageBinding.inflate(
                inflater,
                container,
                false
            )
        val args = arguments?.getParcelable<Args>(ARG_EXTRA)!!
        binding.pageTitle.text = args.title
        binding.pageDescription.text = args.description

        if (args.type == Anchored.TOP) {
            ConstraintSet().apply {
                clone(binding.root)
                setGuidelineBegin(
                    binding.guideline.id,
                    resources.getDimensionPixelSize(R.dimen.title_padding)
                )
            }.also {
                it.applyTo(binding.root)
            }

        }

        return binding.root
    }

    @Parcelize
    class Args(val title: String, val description: String, val type: Anchored) :
        Parcelable

    enum class Anchored {
        TOP, BOTTOM
    }

    companion object {
        private const val ARG_EXTRA = "arg"
        fun newInstance(args: Args) = SwipyScreenFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_EXTRA, args)
            }
        }
    }

}
