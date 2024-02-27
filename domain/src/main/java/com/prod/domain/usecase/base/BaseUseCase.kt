package com.prod.domain.usecase.base

import com.prod.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T : Any?, R : Any?> {
    suspend operator fun invoke(param: T): Flow<Resource<R>>
}