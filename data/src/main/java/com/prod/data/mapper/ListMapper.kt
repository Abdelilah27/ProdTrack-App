package com.prod.data.mapper


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (T) -> R): Flow<List<R>> {
    return map { list -> list.map(transform) }
}