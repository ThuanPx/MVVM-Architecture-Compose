package com.thuanpx.mvvm_architecture_compose.utils.extension

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}