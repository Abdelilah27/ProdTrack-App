package com.prod.data.repository.production

import com.prod.data.dataSource.dao.ProductionDao
import com.prod.data.mapper.EntityProductionMapper
import com.prod.data.mapper.ProductionMapper
import com.prod.data.mapper.mapList
import com.prod.domain.model.Production
import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
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
}