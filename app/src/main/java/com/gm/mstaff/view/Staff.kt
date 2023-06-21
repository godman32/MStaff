package com.gm.mstaff.view

import com.google.gson.annotations.SerializedName

/**
 * Created by @godman on 21/06/23.
 */

data class Staff (
    @SerializedName("nik"             ) var nik            : String? = null,
    @SerializedName("nama"            ) var nama           : String? = null,
    @SerializedName("id_prop"         ) var idProp         : String? = null,
    @SerializedName("nama_prop"       ) var namaProp       : String? = null,
    @SerializedName("jumlah_penduduk" ) var jumlahPenduduk : String? = null
)