package com.tcs.service.repository

import com.tcs.service.model.DeliveryMomentModel
import com.tcs.service.model.LogisticGroupExclusion

interface CustomRepository {

    fun getAllByDesc(modDesc: String): List<DeliveryMomentModel>
    fun findAllByQueryParams(storeNumber: Long?, StreamNumber: Int?,
                             schemaName: String?, deliveryDateTime: String?, orderDateTime: String?,
                             fillDateTime: String?, startFillTime: String?,logisticGroupNumber:Int?): List<DeliveryMomentModel>?
}