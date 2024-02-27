package com.prod.data.di

import com.prod.data.repository.pet.PetRepositoryImp
import com.prod.domain.repository.pet.PetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsPetRepository(petRepositoryImp: PetRepositoryImp)
            : PetRepository
}