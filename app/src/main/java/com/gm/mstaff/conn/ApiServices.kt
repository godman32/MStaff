package com.gm.mstaff.conn

import com.gm.mstaff.view.Staffs
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by @godman on 21/06/23.
 */

interface ApiServices {
    @GET("get_data.php")
    suspend fun getData(@Query("page") page: Int) : Response<Staffs>
}