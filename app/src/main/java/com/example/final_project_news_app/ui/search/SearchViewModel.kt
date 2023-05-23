package com.example.final_project_news_app.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.final_project_news_app.data.api.NewsRepository
import com.example.final_project_news_app.models.NewsResponse
import com.example.final_project_news_app.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    val searchNewsLiveData: MutableLiveData<com.example.final_project_news_app.utils.Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init{
        getSearchNews("")
    }

    fun getSearchNews(query: String) =
        viewModelScope.launch {
            searchNewsLiveData.postValue(Resource.Loading())
            val response = repository.getSearchNews(query = query, pageNumber = searchNewsPage)
            if(response.isSuccessful) {
                response.body().let { res: NewsResponse? ->
                    searchNewsLiveData.postValue(Resource.Success(res))
                }
            }else {
                searchNewsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }
}