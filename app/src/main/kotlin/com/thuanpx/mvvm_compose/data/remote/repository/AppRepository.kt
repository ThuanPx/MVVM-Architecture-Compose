package com.thuanpx.mvvm_compose.data.remote.repository

import com.thuanpx.mvvm_compose.data.remote.api.ApiService
import com.thuanpx.mvvm_compose.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by ThuanPx on 17/09/2021.
 */

interface AppRepository {
}

class DefaultAppRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

}
