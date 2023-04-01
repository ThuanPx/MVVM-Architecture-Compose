package com.thuanpx.mvvm_compose.model.base

import com.squareup.moshi.JsonClass


/**
 * Created by ThuanPx on 16/09/2022.
 */
@JsonClass(generateAdapter = true)
abstract class BaseResponse(
    val code: String? = null,
    var status: String? = null,
    var message: String? = null
)
