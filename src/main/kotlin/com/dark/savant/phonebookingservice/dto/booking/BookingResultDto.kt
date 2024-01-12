package com.dark.savant.phonebookingservice.dto.booking

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(name = "BookingResult", description = "Booking Result data transfer object")
data class BookingResultDto(

    @Schema(description = "Unique identifier of the booking", example = "1", required = false)
    val id: Long?,

    @Schema(description = "ID of the device", example = "101")
    val deviceId: Long,

    @Schema(description = "User who booked", example = "JohnDoe")
    val bookedById: Long,

    @Schema(description = "Start time of the booking", example = "2023-01-01T00:00:00")
    val startTime: LocalDateTime
)