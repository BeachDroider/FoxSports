package com.example.foad.foxsports.matchstats

import android.arch.lifecycle.MutableLiveData
import com.example.foad.foxsports.api.Resource
import com.example.foad.foxsports.api.StatService
import com.example.foad.foxsports.data.MatchStat
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchStatsRepository @Inject constructor(service: StatService) {

    val livedata: MutableLiveData<Resource<List<MatchStat>>> = MutableLiveData()
    private val call = service.getMatchStats()

    init {
        load()
    }

    fun load() {

        // ideally we would first try to recover this data from a local db and if not present or fresh then
        // we would fetch from network. you can look at my other github repository at
        // https://github.com/BeachDroider/SillyWeather

        launch(CommonPool) {
            livedata.postValue(Resource.Loading())
            try {
                //using clone to force refresh on swipe
                val response = call.clone().execute()
                if (response.isSuccessful){
                    response?.body()?.let {
                        livedata.postValue(Resource.Success(it))
                    } ?: postError("server returned empty response")
                } else {
                    postError(response.errorBody().toString())
                }
            } catch (e: Exception) {
                postError(e.message)
            }
        }
    }

    private fun postError(message: String?){

        livedata.postValue(Resource.Error(message))
    }

}