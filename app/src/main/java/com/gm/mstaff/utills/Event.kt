package com.gm.mstaff.utills

/**
 * Created by @godman on 21/06/23.
 */

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled() = if(hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    fun peekContent() = content
}