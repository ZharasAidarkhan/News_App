package com.example.final_project_news_app.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project_news_app.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var binding: FragmentSplashBinding? = null
    private val mBinding get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}