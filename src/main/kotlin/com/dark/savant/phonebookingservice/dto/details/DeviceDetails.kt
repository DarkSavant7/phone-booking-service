package com.dark.savant.phonebookingservice.dto.details

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Details about the phone device")
data class DeviceDetails(
    @Schema(description = "Manufacturer of the device")
    val manufacturer: String,

    @Schema(description = "Model of the device")
    val model: String,

    @Schema(description = "Bands of the device")
    val bands: Bands,

    @Schema(description = "Weight of the device")
    val weight: String,

    @Schema(description = "Description of the device")
    val description: String,

    @Schema(description = "Sub-Model specification of the device")
    @JsonProperty("sub_model_specification")
    val subModelSpecification: SubModelSpecification
)