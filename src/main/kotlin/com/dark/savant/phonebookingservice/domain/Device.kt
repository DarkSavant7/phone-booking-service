package com.dark.savant.phonebookingservice.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "devices")
data class Device(
    @Id val id: Long?,
    val manufacturer: String,
    val model: String,
    @CreatedDate
    val created: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()
)