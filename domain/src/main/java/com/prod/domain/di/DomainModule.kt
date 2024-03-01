package com.prod.domain.di

import com.prod.domain.usecase.pet.addPet.AddPetUseCase
import com.prod.domain.usecase.pet.addPet.AddPetUseCaseImp
import com.prod.domain.usecase.pet.petList.PetListUseCase
import com.prod.domain.usecase.pet.petList.PetListUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsAddPetUseCase(addPetUseCaseImp: AddPetUseCaseImp): AddPetUseCase

    @Binds
    abstract fun bindsPetListUseCase(petListUseCaseImp: PetListUseCaseImp): PetListUseCase
}

