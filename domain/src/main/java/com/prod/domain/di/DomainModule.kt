package com.prod.domain.di

import com.prod.domain.usecase.pet.AddPetUseCase
import com.prod.domain.usecase.pet.AddPetUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsAddPetUseCase(addPetUseCaseImp: AddPetUseCaseImp): AddPetUseCase
}

