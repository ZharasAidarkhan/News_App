package com.example.final_project_news_app.ui.search

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project_news_app.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import com.example.final_project_news_app.utils.Resource
import com.example.final_project_news_app.ui.adapters.NewsAdapter
import com.example.final_project_news_app.ui.search.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val mBinding get() = binding!!

    private val viewModel by viewModels<SearchViewModel>()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        var job: Job? = null
        ed_search.addTextChangedListener { text: Editable? ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    if(it.toString().isNotEmpty()) {
                        viewModel.getSearchNews(query = it.toString())
                    }
                }
            }
        }

        viewModel.searchNewsLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success<*> -> {
                    pag_search_progress_bar.visibility = View.INVISIBLE
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error<*> -> {
                    pag_search_progress_bar.visibility = View.INVISIBLE
                    response.data?.let {
                        Log.e("checkData", "SearchFragment: error: ${it}")
                    }
                }
                is Resource.Loading<*> -> {
                    pag_search_progress_bar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        search_news_adapter.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}