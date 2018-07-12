package com.example.foad.foxsports.matchstats

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.foad.foxsports.api.Resource

class MatchStatsViewModel(application: Application, val matchStatsRepository: MatchStatsRepository) : AndroidViewModel(application) {

    val matchStatListViewModel: MutableLiveData<MatchStatListViewModel?> = MutableLiveData()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()


    init {
        matchStatsRepository.livedata.observeForever {
            when (it) {
                is Resource.Success -> {
                    loading.value = false
                    errorMessage.value = null
                }
                is Resource.Loading -> {
                    loading.value = true
                    errorMessage.value = null
                }
                is Resource.Error -> {
                    loading.value = false
                    errorMessage.value = it.message
                }
            }

            it?.data?.let {
                matchStatListViewModel.value = MatchStatListViewModel(it)
            }
        }
    }

    fun refresh(){
        matchStatsRepository.load()
    }

    class MatchStatViewModelFactory(val application: Application, val matchStatsRepository: MatchStatsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MatchStatsViewModel(application, matchStatsRepository) as T

        }
    }
}