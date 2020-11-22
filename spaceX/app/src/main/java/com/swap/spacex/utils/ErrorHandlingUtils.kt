package com.swap.spacex.utils

import com.swap.spacex.R
import retrofit2.HttpException

open class ErrorHandlingUtils(private val resourceProvider: ResourceProvider) {
    fun getErrorDialogText(exception: HttpException): Pair<String, String?> {
        val errorCode = exception.code()
        val errorMsg: String?

        when (errorCode) {
            400 -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_400)
            }
            401 -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_401)
            }
            403 -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_403)
            }
            404 -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_404)
            }
            500 -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_500)
            }
            else -> {
                errorMsg = resourceProvider.getString(R.string.error_msg_generic)

            }
        }

        val errorTitle = "$errorCode Error"
        return Pair(errorTitle, errorMsg)
    }
}