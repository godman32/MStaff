package com.gm.mstaff.utills.ext

import java.text.DecimalFormat


/**
 * Created by @godman on 21/06/23.
 */

fun Double.format(): String {
    if (this == 0.0)
        return "0"

    val dec = DecimalFormat("###,###,###")
    val result = dec.format(this)
    return if (result[0].toString() == ".") {
        "0$result"
    } else {
        result
    }
}