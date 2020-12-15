package com.tcs.service.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.env.PropertySource
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory
import org.springframework.stereotype.Repository
import java.io.IOException

//
//class YamlPropertySourceFactory : PropertySourceFactory {
//     @Throws(IOException::class)
//     override fun createPropertySource(name: String?, encodedResource: EncodedResource): PropertySource<*> {
//          val factory = YamlPropertiesFactoryBean()
//          factory.setResources(encodedResource.resource)
//          val properties = factory.getObject()
//          return PropertiesPropertySource(encodedResource.resource.filename, properties)
//     }
//}

class EnviromentVars {


//    lateinit var env: Environment

//     var secret1: String = System.getenv("secretstore")
//     var secret2: String = System.getenv("secretkey")
//     var sec = Key("http.port", intType)
//    @Value("secretstore")

    @Value("\${secretstore}")
    var secret3: String? = null
//     val secret4 = Environment.getProperty("secretstore")
//    val secret1 = env.getProperty("secretstore")
//    val secret2 = env.getProperty("secretkey")
}

//@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties
//class EnviromentVars {
//     val secretStore: String? = null
//     val secretKey: String? = null
////    private val enabled = false
////    private val servers: List<String> = ArrayList() // standard getters and setters
//}