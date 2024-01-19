package com.dark.savant.phonebookingservice.domain

import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "bookings")
data class Booking(
    @Id val id: Long? = null,
    val deviceId: Long,
    val userId: Long,
    val endTime: LocalDateTime? = null,
    @CreatedDate
    val created: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()
) {
    fun toResultDto(): BookingResultDto = BookingResultDto(
        id = this.id,
        deviceId = this.deviceId,
        bookedById = this.userId,
        startTime = this.created
    )

}