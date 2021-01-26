package awesome.shizzle.playground.fragcomms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import awesome.shizzle.playground.R
import awesome.shizzle.playground.databinding.FragmentParentBinding

class ParentFragment : Fragment(R.layout.fragment_parent) {

    private lateinit var binding: FragmentParentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Listen to child fragment results
        childFragmentResultListener { result ->
            when (result) {
                is HodlFragmentResult -> {
                    binding.child1ResultText.text = result.messageFromHodl
                    if (result.sendToActivity) {

                        // Send result to parent, in this case the hosting activity
                        setFragmentResult(
                            ParentFragmentResult(
                                message = result.messageFromHodl
                            )
                        )
                    }
                }
                is DyorFragmentResult -> {
                    binding.child2ResultText.text = result.messageFromDyor
                    if (result.sendToActivity) {
                        setFragmentResult(
                            ParentFragmentResult(
                                message = result.messageFromDyor
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentParentBinding.bind(view)
        childFragmentManager.beginTransaction()
            .replace(R.id.child1_fragment_container, ChildHodlFragment.newInstance())
            .replace(R.id.child2_fragment_container, ChildDyorFragment.newInstance())
            .commit()

    }

    companion object {
        fun newInstance() = ParentFragment()
    }

}
