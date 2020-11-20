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
    override fun findAllsoftdelete():MutableList<DeliveryMomentModel>{
        var queryObject = Query()
        var criteria = Criteria();
        var criteria1 = Criteria.where("isdeleted").isEqualTo(false);
        queryObject.addCriteria(criteria1);
        return mongoTemplate.find(queryObject,
                DeliveryMomentModel::class.java)

    }

    override fun findAllByQueryParams(storeNumber: Long?, StreamNumber: Int?,
                                      schemaName: String?,deliveryDateTime:String?,orderDateTime:String?,
                                      fillDateTime:String?,
                                      startFillTime:String?,deliveryDateFrom:String?, deliveryDateTo:String?,
                                      orderDateFrom:String?, orderDateTo:String?, fillDateFrom:String?,
                                      fillDateTo:String?, startFillTimeFrom:String?, startFillTimeTo:String?
                                      ,logisticGroupNumber:Int?,mainDeliveryFlag: String?): List<DeliveryMomentModel>?{

        var queryObject = Query()
        var criteria = Criteria();
        var criteria1 = Criteria.where("isdeleted").isEqualTo(false);
        queryObject.addCriteria(criteria1);
        if( storeNumber != null) {

            var criteria1 = Criteria.where("storeNumber").isEqualTo(storeNumber);
            queryObject.addCriteria(criteria1);
        }

        if( StreamNumber != null) {
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
            var criteria1 = Criteria.where("startFillTime").isEqualTo(startFillTime);
            queryObject.addCriteria(criteria1);
        }
        if( deliveryDateFrom != null && deliveryDateTo != null){
            var criteria1 = Criteria.where("deliveryDateTime").
            gte(deliveryDateFrom).andOperator(Criteria.where("deliveryDateTime").lte(deliveryDateTo))
            queryObject.addCriteria(criteria1);
        }
        if( deliveryDateFrom != null && deliveryDateTo== null) {
            var criteria1 = Criteria.where("deliveryDateTime").gte(deliveryDateFrom);
            queryObject.addCriteria(criteria1);
        }
        if( deliveryDateTo != null && deliveryDateFrom== null) {
            var criteria1 = Criteria.where("deliveryDateTime").lte(deliveryDateTo);
            queryObject.addCriteria(criteria1);
        }
        if( orderDateFrom != null && orderDateTo != null){
            var criteria1 = Criteria.where("orderDateTime").
            gte(orderDateFrom).andOperator(Criteria.where("orderDateTime").lte(orderDateTo))
            queryObject.addCriteria(criteria1);
        }
        if( orderDateFrom != null && orderDateTo == null) {
            var criteria1 = Criteria.where("orderDateTime").gte(orderDateFrom);
            queryObject.addCriteria(criteria1);
        }

        if( orderDateTo != null && orderDateFrom == null) {
            var criteria1 = Criteria.where("orderDateTime").lte(orderDateTo);
            queryObject.addCriteria(criteria1);
        }
        if( fillDateFrom != null && fillDateTo != null){
            var criteria1 = Criteria.where("fillDateTime").
            gte(fillDateFrom).andOperator(Criteria.where("fillDateTime").lt(fillDateTo))
            queryObject.addCriteria(criteria1);
        }

        if( fillDateFrom != null && fillDateTo == null) {
            var criteria1 = Criteria.where("fillDateTime").gte(fillDateFrom);
            queryObject.addCriteria(criteria1);
        }
        if( fillDateTo != null && fillDateFrom == null) {
            var criteria1 = Criteria.where("fillDateTime").lte(fillDateTo);
            queryObject.addCriteria(criteria1);
        }
        if( startFillTimeFrom != null && startFillTimeTo != null){
            var criteria1 = Criteria.where("startFillTime").
            gte(startFillTimeFrom).andOperator(Criteria.where("startFillTime").lt(startFillTimeTo))
            queryObject.addCriteria(criteria1);
        }

        if( startFillTimeFrom != null && startFillTimeTo == null) {
            var criteria1 = Criteria.where("startFillTime").gte(startFillTimeFrom);
            queryObject.addCriteria(criteria1);
        }
        if( startFillTimeTo != null &&  startFillTimeFrom == null) {
            var criteria1 = Criteria.where("startFillTime").lte(startFillTimeTo);
            queryObject.addCriteria(criteria1);
        }
        if(logisticGroupNumber != null){
            var criteria1 = Criteria.where("logisticGroupExclusion.logisticGroupNumber").isEqualTo(logisticGroupNumber);
        }
        if( mainDeliveryFlag != null) {

            var criteria1 = Criteria.where("mainDeliveryFlag").isEqualTo("J");
            queryObject.addCriteria(criteria1);
        }
         print(queryObject)
        return mongoTemplate.find(queryObject,
                DeliveryMomentModel::class.java)
    }

    override fun getbyanyparam(storeNumber: Long?, streamNumber: Int?, deliveryDateTime: String?,
                               orderDateTime: String?, fillDateTime: String?): List<DeliveryMomentModel> {
        var query = Query()

        var criteria = Criteria()
        criteria.andOperator(
                Criteria.where("storeNumber").isEqualTo(storeNumber),
                Criteria.where("streamNumber").isEqualTo(streamNumber).orOperator(
                        Criteria.where("deliveryDateTime").isEqualTo(deliveryDateTime),
                        Criteria.where("orderDateTime").isEqualTo(orderDateTime),
                        Criteria.where("fillDateTime").isEqualTo(fillDateTime)
                )
        )

        var toPrint = query.addCriteria(criteria);
        return mongoTemplate.find(toPrint,
                DeliveryMomentModel::class.java)





    }


}