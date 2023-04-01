package com.thuanpx.mvvm_compose.core.network

data class NetworkError(
    val errorCode: String?,
    val message: String?,
    val status: String?
)