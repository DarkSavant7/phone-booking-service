package com.dark.savant.phonebookingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class PhoneBookingServiceApplication

fun main(args: Array<String>) {
    runApplication<PhoneBookingServiceApplication>(*args)
}
