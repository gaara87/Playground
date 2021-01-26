package awesome.shizzle.playground.fragcomms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import awesome.shizzle.playground.R
import awesome.shizzle.playground.databinding.FragmentChildrenBinding

class ChildHodlFragment : Fragment(R.layout.fragment_children) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentChildrenBinding.bind(view)
        binding.sendToParent.setOnClickListener {

            // Send result to the parent fragment
            setFragmentResult(
                HodlFragmentResult(
                    messageFromHodl = binding.editText.text.toString(),
                    sendToActivity = binding.sendToActivity.isChecked
                )
            )
        }
    }

    companion object {
        fun newInstance() = ChildHodlFragment()

    }
}
