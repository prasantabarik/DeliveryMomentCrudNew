package com.tcs.service.service

import com.tcs.service.configs.DataBaseConnectionConfig
import com.tcs.service.constant.ExceptionMessage
import com.tcs.service.error.customexception.DataNotFoundException
import com.tcs.service.model.DeliveryMomentModel
import com.tcs.service.model.Model
import com.tcs.service.repository.Repository
import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Service

@Service
class Service(private val repository: Repository) {
    val logger = logger()
    fun getById(id: String): Model {
        logger.info("Before Cast")
        return Model(repository.findById(id.toInt()).get())
    }
    fun get(): MutableList<DeliveryMomentModel>{


        val result = repository.findAllsoftdelete()
        return result.toMutableList()
    }

    fun getByQueryParam(storeNumber: Long?, StreamNumber: Int?,
                        schemaName: String?,deliveryDateTime:String?,orderDateTime:String?,
                        fillDateTime:String?,
                        startFillTime:String?, deliveryDateFrom:String?, deliveryDateTo:String?,
                         orderDateFrom:String?, orderDateTo:String?, fillDateFrom:String?,
                         fillDateTo:String?, startFillTimeFrom:String?, startFillTimeTo:String?,
                        logisticGroupNumber:Int?,mainDeliveryFlag: String?): MutableList<DeliveryMomentModel>{

        when {
            storeNumber == null && StreamNumber == null &&
                    schemaName == null && deliveryDateTime == null && deliveryDateFrom == null && deliveryDateTo == null
                    && startFillTime == null && startFillTimeFrom == null && startFillTimeTo == null && orderDateTime == null && orderDateFrom == null && orderDateTo == null
                    && logisticGroupNumber== null && fillDateTime == null && fillDateFrom == null && fillDateTo == null
                    && mainDeliveryFlag == null -> return get()
        }
        val result = repository.findAllByQueryParams(storeNumber, StreamNumber,
                schemaName,deliveryDateTime,orderDateTime,
        fillDateTime, startFillTime,deliveryDateFrom,deliveryDateTo,orderDateFrom,orderDateTo,
                fillDateFrom,fillDateTo,startFillTimeFrom,startFillTimeTo,logisticGroupNumber,mainDeliveryFlag) ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)

        return result.toMutableList()
    }

    fun save(model: DeliveryMomentModel)
    {
        DataBaseConnectionConfig.mongoTemplate().save(model)

    }

    fun delete(id: String)
    {

        val model :DeliveryMomentModel = repository.getById(id)[0]
        model.isdeleted = true

        DataBaseConnectionConfig.mongoTemplate().save(model)

    }

    fun getByQueryParamanymatch(storeNumber: Long?, streamNumber: Int?, deliveryDateTime: String?, orderDateTime: String?, fillDateTime: String?): List<DeliveryMomentModel> {

     return   repository.getbyanyparam(storeNumber, streamNumber,
                deliveryDateTime,orderDateTime, fillDateTime)

    }

}