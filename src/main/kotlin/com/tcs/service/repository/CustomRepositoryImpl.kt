package com.tcs.service.repository

import com.tcs.service.model.DeliveryMomentModel
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class CustomRepositoryImpl(private val mongoTemplate: MongoTemplate) : CustomRepository{


    override fun getAllByDesc(modDesc: String): List<DeliveryMomentModel>{
        return mongoTemplate.find(Query(Criteria.where("modDesc").`is`(modDesc)),
        DeliveryMomentModel::class.java)
    }

    override fun findAllByQueryParams(storeNumber: Long?, StreamNumber: Int?,
                                      schemaName: String?, deliveryDateTime: String?, orderDateTime: String?,
                                      fillDateTime: String?, startFillTime: String?,logisticGroupNumber:Int?): List<DeliveryMomentModel>?{
        var queryObject = Query()
        var criteria = Criteria();

        if( storeNumber != null) {

            var criteria1 = Criteria.where("storeNumber").isEqualTo(storeNumber);
            queryObject.addCriteria(criteria1);
        }

        if( StreamNumber != null) {
//                orCriterias?.add(Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStreamNumber))
            var criteria1 = Criteria.where("StreamNumber").isEqualTo(StreamNumber);
            queryObject.addCriteria(criteria1);
        }


        if( schemaName != null) {
            var criteria1 = Criteria.where("schemaName").isEqualTo(schemaName);
            queryObject.addCriteria(criteria1);
        }

        if( deliveryDateTime != null) {
            var criteria1 = Criteria.where("deliveryDateTime").isEqualTo(deliveryDateTime);
            queryObject.addCriteria(criteria1);
        }

        if( orderDateTime != null) {
            var criteria1 = Criteria.where("orderDateTime").isEqualTo(orderDateTime);
            queryObject.addCriteria(criteria1);
        }

        if( fillDateTime != null) {
            var criteria1 = Criteria.where("fillDateTime").isEqualTo(fillDateTime);
            queryObject.addCriteria(criteria1);
        }

        if( startFillTime != null) {
//                orCriterias?.add(Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStreamNumber))
            var criteria1 = Criteria.where("startFillTime").isEqualTo(startFillTime);
            queryObject.addCriteria(criteria1);
        }
        if(logisticGroupNumber != null){
            var criteria1 = Criteria.where("logisticGroupExclusion.logisticGroupNumber").isEqualTo(logisticGroupNumber);
        }

        queryObject.addCriteria(criteria);
        println("template"+mongoTemplate.find(queryObject,
                DeliveryMomentModel::class.java))
        return mongoTemplate.find(queryObject,
                DeliveryMomentModel::class.java)
    }
}