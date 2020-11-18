package com.tcs.service.repository

import com.tcs.service.model.DeliveryMomentModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : MongoRepository<DeliveryMomentModel, Int>, CustomRepository {

    fun deleteById(id: String)

}