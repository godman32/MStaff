package com.gm.mstaff.conn

/**
 * Created by @godman on 21/06/23.
 */

class AppRepository() {

    suspend fun getData(page:Int) = NetworkBuilder.api.getData(page)
}