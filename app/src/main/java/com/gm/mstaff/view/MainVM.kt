package com.gm.mstaff.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gm.mstaff.R
import com.gm.mstaff.app.MyApplication
import com.gm.mstaff.conn.AppRepository
import com.gm.mstaff.utills.Event
import com.gm.mstaff.utills.Resource
import com.gm.mstaff.utills.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

/**
 * Created by @godman on 21/06/23.
 */

class MainVM (app: Application, private val repository: AppRepository) : AndroidViewModel(app) {

    private val _staffs = MutableLiveData<Event<Resource<Staffs>>>()
    val staffs: LiveData<Event<Resource<Staffs>>> = _staffs

    var page= 1

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        loadData()
    }

    private suspend fun loadData() {
        _staffs.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = repository.getData(page)
                _staffs.postValue(handleData(response))
            } else {
                _staffs.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _staffs.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _staffs.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.conversion_error
                            )
                        ))
                    )
                }
            }
        }
    }

    private fun handleData(response: Response<Staffs>): Event<Resource<Staffs>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}