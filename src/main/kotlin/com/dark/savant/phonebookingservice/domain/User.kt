package com.dark.savant.phonebookingservice.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "users")
data class User(
    @Id val id: Long?,
    val login: String,
    val password: String,
    val email: String? = null,
    @CreatedDate
    val created: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()
)