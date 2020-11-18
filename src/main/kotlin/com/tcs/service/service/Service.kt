package com.tcs.service.service

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
        return Model(repository.findById(id.toInt()).get() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND))
    }


    fun get(): MutableList<DeliveryMomentModel>{
        //The below lines of code is for POC on Mongo Template
        //repository.getAllByDesc("Sample").forEach{i -> println(i.modId)}

        var result = repository.findAll() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)
        println(result)
        return result.toMutableList()
    }

    fun getByQueryParam(storeNumber: Long?, StreamNumber: Int?,
                        schemaName: String?, deliveryDateTime: String?, orderDateTime: String?,
                        fillDateTime: String?, startFillTime: String?,logisticGroupNumber:Int?): MutableList<DeliveryMomentModel>{
        //The below lines of code is for POC on Mongo Template
        //to check if any of the query parameters is null
        when {
            storeNumber == null && StreamNumber == null &&
                    schemaName == null && deliveryDateTime == null
                    && orderDateTime == null && fillDateTime == null
                    && logisticGroupNumber== null
                    && startFillTime == null -> return get()
        }
        var result = repository.findAllByQueryParams(storeNumber, StreamNumber,
                schemaName,deliveryDateTime,orderDateTime,fillDateTime, startFillTime,logisticGroupNumber) ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)
               println(result+"service")
        return result.toMutableList()
    }

    fun save(model: DeliveryMomentModel)
    {
        repository.save(model)
    }

    fun delete(id: String)
    {
        repository.deleteById(id)
    }

}