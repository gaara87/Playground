package awesome.shizzle.playground.fragcomms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import awesome.shizzle.playground.R
import awesome.shizzle.playground.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity(R.layout.activity_host) {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        val binding = ActivityHostBinding.bind(window.decorView)
        supportFragmentManager.beginTransaction()
            .replace(binding.parentFragmentContainer.id, ParentFragment.newInstance())
            .commit()

        // Receive results from the inflated child fragment(s)
        setFragmentResultListener { result ->
            when (result) {
                is ParentFragmentResult -> {
                    binding.messageFromChildren.text = result.message
                }
            }
        }
    }
}
