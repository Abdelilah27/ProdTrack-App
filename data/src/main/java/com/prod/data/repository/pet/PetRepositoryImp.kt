package com.prod.data.repository.pet

import com.prod.data.dataSource.dao.PetDao
import com.prod.data.mapper.PetMapper
import com.prod.domain.model.Pet
import com.prod.domain.repository.pet.PetRepository
import javax.inject.Inject

class PetRepositoryImp @Inject constructor(private val petDao: PetDao) : PetRepository {

    override suspend fun addPet(pet: Pet) {
        petDao.addPet(PetMapper.map(pet))
    }
}