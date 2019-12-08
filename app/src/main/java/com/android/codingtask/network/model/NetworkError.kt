package com.android.codingtask.network.model

data class NetworkError(
    val description: String,
    val recommendation: String?
) {
    fun asString(): String {
        recommendation?.let {
            return "$description\n$recommendation"
        }
        return description
    }
}

