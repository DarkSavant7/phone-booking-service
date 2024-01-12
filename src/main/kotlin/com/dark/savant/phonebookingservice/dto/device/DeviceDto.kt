package com.dark.savant.phonebookingservice.dto.device

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data transfer object for Device")
data class DeviceDto(
    @Schema(description = "Unique identifier of the Device")
    val id: Long?,

    @Schema(description = "Manufacturer of the Device")
    val manufacturer: String,

    @Schema(description = "Model of the Device")
    val model: String,
)