package com.example.final_project_news_app.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project_news_app.R
import com.example.final_project_news_app.databinding.FragmentDetailsBinding
import com.example.final_project_news_app.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var binding: FragmentFavoriteBinding? = null
    private val mBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}