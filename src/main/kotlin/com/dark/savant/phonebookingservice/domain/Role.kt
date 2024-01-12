package com.dark.savant.phonebookingservice.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "roles")
data class Role(
    @Id val id: Int?,
    val name: String,
    @CreatedDate
    val created: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()
)