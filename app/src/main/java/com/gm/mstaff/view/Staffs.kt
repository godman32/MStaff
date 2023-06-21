package com.gm.mstaff.view

import com.google.gson.annotations.SerializedName

/**
 * Created by @godman on 21/06/23.
 */

data class Staffs (
    @SerializedName("data" ) var staffs : ArrayList<Staff> = arrayListOf()
)