package com.dark.savant.phonebookingservice.dto.details

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data class representing GSM bands")
data class Bands(
    @Schema(description = "List of 2G bands")
    val `2g`: List<String>,

    @Schema(description = "List of 3G bands")
    val `3g`: List<String>,

    @Schema(description = "List of 4G bands")
    val `4g`: List<String>,

    @Schema(description = "List of 5G bands")
    val `5g`: List<String>
)