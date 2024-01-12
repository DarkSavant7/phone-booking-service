package com.dark.savant.phonebookingservice.dto.booking

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Data transfer object for booking information")
data class BookingDto(
    @Schema(description = "ID of the booking", example = "1", required = false)
    val id: Long?,

    @Schema(description = "ID of the device", example = "1")
    val deviceId: Long,

    @Schema(description = "Manufacturer of the device", example = "Apple")
    val deviceManufacturer: String,

    @Schema(description = "Model of the device", example = "iPhone X")
    val deviceModel: String,

    @Schema(description = "ID of the person who booked", example = "1")
    val bookedById: Long,

    @Schema(description = "Name of the person who booked", example = "JohnDoe")
    val bookedBy: String,

    @Schema(description = "Start time of the booking", example = "2023-01-01T00:00:00")
    val startTime: LocalDateTime,

    @Schema(
        description = "End time of the booking",
        example = "2023-01-01T01:00:00",
        required = false
    )
    val endTime: LocalDateTime? = null,
)