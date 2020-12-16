package com.tcs.service.repository

import com.tcs.service.configs.DataBaseConnectionConfig
import com.tcs.service.model.DeliveryMomentModel
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class CustomRepositoryImpl : CustomRepository{



    override fun findAllsoftdelete():MutableList<DeliveryMomentModel>{
        val queryObject = Query()
        val criteria1 = Criteria.where("isdeleted").isEqualTo(false)
        queryObject.addCriteria(criteria1)


        return DataBaseConnectionConfig.mongoTemplate().find(queryObject, DeliveryMomentModel::class.java)


    }

    override fun findAllByQueryParams(storeNumber: Long?, StreamNumber: Int?,
                                      schemaName: String?,deliveryDateTime:String?,orderDateTime:String?,
                                      fillDateTime:String?,
                                      startFillTime:String?,deliveryDateFrom:String?, deliveryDateTo:String?,
                                      orderDateFrom:String?, orderDateTo:String?, fillDateFrom:String?,
                                      fillDateTo:String?, startFillTimeFrom:String?, startFillTimeTo:String?
                                      ,logisticGroupNumber:Int?,mainDeliveryFlag: String?): List<DeliveryMomentModel>?{

        val queryObject = Query()
        var criteria1 = Criteria.where("isdeleted").isEqualTo(false)
        queryObject.addCriteria(criteria1)
        if( storeNumber != null) {

             criteria1 = Criteria.where("storeNumber").isEqualTo(storeNumber)
            queryObject.addCriteria(criteria1)
        }

        if( StreamNumber != null) {
             criteria1 = Criteria.where("StreamNumber").isEqualTo(StreamNumber)
            queryObject.addCriteria(criteria1)
        }

        if( schemaName != null) {
             criteria1 = Criteria.where("schemaName").isEqualTo(schemaName)
            queryObject.addCriteria(criteria1)
        }
        if( deliveryDateTime != null) {
            criteria1 = Criteria.where("deliveryDateTime").isEqualTo(deliveryDateTime)
            queryObject.addCriteria(criteria1)
        }

        if( orderDateTime != null) {
             criteria1 = Criteria.where("orderDateTime").isEqualTo(orderDateTime)
            queryObject.addCriteria(criteria1)
        }

        if( fillDateTime != null) {
            criteria1 = Criteria.where("fillDateTime").isEqualTo(fillDateTime)
            queryObject.addCriteria(criteria1)
        }

        if( startFillTime != null) {
            criteria1 = Criteria.where("startFillTime").isEqualTo(startFillTime)
            queryObject.addCriteria(criteria1)
        }
        if( deliveryDateFrom != null && deliveryDateTo != null){
             criteria1 = Criteria.where("deliveryDateTime").
            gte(deliveryDateFrom).andOperator(Criteria.where("deliveryDateTime").lte(deliveryDateTo))
            queryObject.addCriteria(criteria1)
        }
        if( deliveryDateFrom != null && deliveryDateTo == null) {
             criteria1 = Criteria.where("deliveryDateTime").gte(deliveryDateFrom)
            queryObject.addCriteria(criteria1)
        }
        if( deliveryDateTo != null && deliveryDateFrom == null) {
             criteria1 = Criteria.where("deliveryDateTime").lte(deliveryDateTo)
            queryObject.addCriteria(criteria1)
        }
        if( orderDateFrom != null && orderDateTo != null){
             criteria1 = Criteria.where("orderDateTime").
            gte(orderDateFrom).andOperator(Criteria.where("orderDateTime").lte(orderDateTo))
            queryObject.addCriteria(criteria1)
        }
        if( orderDateFrom != null && orderDateTo == null) {
            criteria1 = Criteria.where("orderDateTime").gte(orderDateFrom)
            queryObject.addCriteria(criteria1)
        }

        if( orderDateTo != null && orderDateFrom == null) {
           criteria1 = Criteria.where("orderDateTime").lte(orderDateTo)
            queryObject.addCriteria(criteria1)
        }
        if( fillDateFrom != null && fillDateTo != null){
             criteria1 = Criteria.where("fillDateTime").
            gte(fillDateFrom).andOperator(Criteria.where("fillDateTime").lt(fillDateTo))
            queryObject.addCriteria(criteria1)
        }

        if( fillDateFrom != null && fillDateTo == null) {
             criteria1 = Criteria.where("fillDateTime").gte(fillDateFrom)
            queryObject.addCriteria(criteria1)
        }
        if( fillDateTo != null && fillDateFrom == null) {
           criteria1 = Criteria.where("fillDateTime").lte(fillDateTo)
            queryObject.addCriteria(criteria1)
        }
        if( startFillTimeFrom != null && startFillTimeTo != null){
          criteria1 = Criteria.where("startFillTime").
            gte(startFillTimeFrom).andOperator(Criteria.where("startFillTime").lt(startFillTimeTo))
            queryObject.addCriteria(criteria1)
        }

        if( startFillTimeFrom != null && startFillTimeTo == null) {
         criteria1 = Criteria.where("startFillTime").gte(startFillTimeFrom)
            queryObject.addCriteria(criteria1)
        }
        if( startFillTimeTo != null &&  startFillTimeFrom == null) {
            criteria1 = Criteria.where("startFillTime").lte(startFillTimeTo)
            queryObject.addCriteria(criteria1)
        }
        if(logisticGroupNumber != null){
            criteria1 = Criteria.where("logisticGroupExclusion.logisticGroupNumber").isEqualTo(logisticGroupNumber)
            queryObject.addCriteria(criteria1)
        }
        if( mainDeliveryFlag != null) {

             criteria1 = Criteria.where("mainDeliveryFlag").isEqualTo("J")
            queryObject.addCriteria(criteria1)
        }


        return DataBaseConnectionConfig.mongoTemplate().find(queryObject, DeliveryMomentModel::class.java)

    }

    override fun getbyanyparam(storeNumber: Long?, streamNumber: Int?, deliveryDateTime: String?,
                               orderDateTime: String?, fillDateTime: String?): List<DeliveryMomentModel> {
        val query = Query()

        val criteria = Criteria()
        criteria.andOperator(
                Criteria.where("storeNumber").isEqualTo(storeNumber),
                Criteria.where("streamNumber").isEqualTo(streamNumber).orOperator(
                        Criteria.where("deliveryDateTime").isEqualTo(deliveryDateTime),
                        Criteria.where("orderDateTime").isEqualTo(orderDateTime),
                        Criteria.where("fillDateTime").isEqualTo(fillDateTime)
                )
        )

        val toPrint = query.addCriteria(criteria)


        return DataBaseConnectionConfig.mongoTemplate().find(toPrint, DeliveryMomentModel::class.java)



    }

    override fun getById(id: String?): List<DeliveryMomentModel> {
        val query = Query()



        val toPrint = query.addCriteria(Criteria.where("id").isEqualTo(id))


        return DataBaseConnectionConfig.mongoTemplate().find(toPrint, DeliveryMomentModel::class.java)



    }


}