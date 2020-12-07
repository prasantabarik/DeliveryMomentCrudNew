package com.tcs.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import khttp.get

@SpringBootApplication
class ServiceKotlinTemplateApplication

fun main(args: Array<String>) {
    runApplication<ServiceKotlinTemplateApplication>(*args)
    //get(url = "http://localhost:3500/v1.0/secrets/azurekeyvault/deliverymomentdbapi")
    println(get(url = "http://localhost:3500/v1.0/secrets/azurekeyvault/deliverymomentdbapi?metadata.namespace=edppublic-deliverymomentcrud-dev"))
    println(get(url = "http://localhost:3500/v1.0/secrets/azurekeyvault/deliverycruddb?metadata.namespace=edppublic-deliverymomentcrud-dev"))
    
}
