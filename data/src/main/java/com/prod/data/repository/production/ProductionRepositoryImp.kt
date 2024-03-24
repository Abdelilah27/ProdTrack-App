package com.prod.data.repository.production

import com.prod.data.dataSource.dao.ProductionDao
import com.prod.data.mapper.EntityProductionMapper
import com.prod.data.mapper.EntityStockMapper
import com.prod.data.mapper.ProductionMapper
import com.prod.data.mapper.mapList
import com.prod.domain.model.Production
import com.prod.domain.model.Stock
import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductionRepositoryImp @Inject constructor(private val productionDao: ProductionDao) :
    ProductionRepository {
    override suspend fun addProduction(production: Production) {
        productionDao.addProduction(ProductionMapper.map(production))
    }


    override fun getAllProductions(): Flow<List<Production>> {
        return productionDao.getAllProductions().mapList { productionEntity ->
            EntityProductionMapper.map(productionEntity)
        }
    }

    override suspend fun deleteProductionById(id: Int) {
        productionDao.deleteProductionById(id)
    }

    override suspend fun updateProduction(
        productionId: Int,
        petId: Int,
        stockId: Int,
        date: String,
        producing: Float,
        quantity: Float
    ) {
        productionDao.updateProduction(
            productionId = productionId,
            petId = petId,
            stockId = stockId,
            date = date,
            producing = producing,
            quantity = quantity
        )
    }

    override fun getProductionById(id: Int): Flow<Production> {
        return productionDao.getPetProductionById(id).map { productionEntity ->
            EntityProductionMapper.map(productionEntity)
        }
    }
}