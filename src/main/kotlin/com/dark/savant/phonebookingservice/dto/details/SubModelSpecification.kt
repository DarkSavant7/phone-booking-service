package com.dark.savant.phonebookingservice.dto.details

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Details about the model specification")
data class SubModelSpecification(
    @field:Schema(description = "Processor information", required = true)
    val processor: String,

    @field:Schema(description = "RAM information", required = true)
    @JsonProperty("RAM")
    val ram: String,

    @field:Schema(description = "Video chip information", required = true)
    @JsonProperty("video_chip")
    val videoChip: String,

    @field:Schema(description = "Screen resolution", required = true)
    @JsonProperty("screen_resolution")
    val screenResolution: String,

    @field:Schema(description = "Screen size", required = true)
    @JsonProperty("screen_size")
    val screenSize: String,

    @field:Schema(description = "Battery size", required = true)
    @JsonProperty("battery_size")
    val batterySize: String
)