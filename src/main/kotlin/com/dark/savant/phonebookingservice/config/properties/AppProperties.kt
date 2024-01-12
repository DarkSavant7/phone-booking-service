package com.dark.savant.phonebookingservice.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConfigurationProperties(prefix = "app")
data class AppProperties(
    @NestedConfigurationProperty
    val detailsService: DetailsServiceProperties
) {
    data class DetailsServiceProperties(val host: String, val url: String)
}