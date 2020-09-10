package awesome.shizzle.playground.swipies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import awesome.shizzle.playground.R
import awesome.shizzle.playground.swipies.SwipyScreenFragment.Anchored
import awesome.shizzle.playground.databinding.FragmentSwipiesBinding

class SwipiesFragment : Fragment() {

    private lateinit var binding: FragmentSwipiesBinding

    private val swipyAdapter by lazy { SwipyPagerAdapter(this) }

    private var cashPageChangeCallback: CashSignUpAnimationPageChangeCallback? = null
    private var cashAnimationTransformingPageChangeCallback: CashAnimationTransformingPageChangeCallback? =
        null
    private var pageTransformerPageChangeCallback: PageTransformerPageChangeCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSwipiesBinding.inflate(inflater, container, false)
        binding.pager.offscreenPageLimit = 2
        binding.pager.adapter = swipyAdapter
        CashSignUpAnimationPageChangeCallback(binding.lottie).also {
            cashPageChangeCallback = it
            binding.pager.registerOnPageChangeCallback(it)
        }
        CashAnimationTransformingPageChangeCallback(binding.root, binding.guideline).also {
            cashAnimationTransformingPageChangeCallback = it
            binding.pager.registerOnPageChangeCallback(it)
        }
        PageTransformerPageChangeCallback(binding.pager).also {
            pageTransformerPageChangeCallback = it
            binding.pager.registerOnPageChangeCallback(it)
        }
        binding.lottie.setAnimation(R.raw.cash)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cashPageChangeCallback?.let {
            binding.pager.unregisterOnPageChangeCallback(it)
        }
        cashPageChangeCallback = null

        cashAnimationTransformingPageChangeCallback?.let {
            binding.pager.unregisterOnPageChangeCallback(it)
        }
        cashAnimationTransformingPageChangeCallback = null
        pageTransformerPageChangeCallback?.let {
            binding.pager.unregisterOnPageChangeCallback(it)
        }
        pageTransformerPageChangeCallback = null
    }

    private inner class SwipyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 6

        override fun createFragment(position: Int): Fragment {
            val contentAnchor = if (position == 0) Anchored.TOP else Anchored.BOTTOM
            val title = requireContext().resources.getStringArray(R.array.titles)[position]
            val description =
                requireContext().resources.getStringArray(R.array.descriptions)[position]
            return SwipyScreenFragment.newInstance(
                SwipyScreenFragment.Args(
                    title = title,
                    description = description,
                    type = contentAnchor
                )
            )
        }

    }

}

