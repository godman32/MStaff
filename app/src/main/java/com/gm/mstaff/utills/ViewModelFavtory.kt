package com.gm.mstaff.utills

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gm.mstaff.view.MainVM
import com.gm.mstaff.conn.AppRepository

/**
 * Created by @godman on 21/06/23.
 */

class ViewModelFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVM::class.java)) {
            return MainVM(app, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}