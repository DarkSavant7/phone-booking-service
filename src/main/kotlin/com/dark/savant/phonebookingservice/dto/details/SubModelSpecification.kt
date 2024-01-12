package com.dark.savant.phonebookingservice.dto.details

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Details about the model specification")
data class SubModelSpecification(
    @field:Schema(description = "Processor information", required = true)
    val processor: String,

    @field:Schema(description = "RAM information", required = true)
    val ram: String,

    @field:Schema(description = "Video chip information", required = true)
    val videoChip: String,

    @field:Schema(description = "Screen resolution", required = true)
    val screenResolution: String,

    @field:Schema(description = "Screen size", required = true)
    val screenSize: String,

    @field:Schema(description = "Battery size", required = true)
    val batterySize: String
)