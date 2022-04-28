package com.example.a220402

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.FragmentProfileBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()
    }

    fun initTransactionEvent() {
        val fragment1 = PfFollowerAdapter()
        val fragment2 = PfRepoAdapter()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_main, fragment1)
            .commit()

        binding.followerbtn.setOnClickListener {
            if (position == REPO_POSITION) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_POSITION
            }
        }

        binding.repobtn.setOnClickListener {
            if (position == FOLLOWER_POSITION) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_POSITION
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}