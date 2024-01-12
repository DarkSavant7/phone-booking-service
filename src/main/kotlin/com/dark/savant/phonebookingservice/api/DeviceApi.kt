package com.dark.savant.phonebookingservice.api

import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.tags.Tags
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Represents an API for managing devices.
 */
@Tags(value = [Tag(name = "Device Management API")])
interface DeviceApi {

    /**
     * Retrieves a list of devices.
     *
     * @return A [Flux] of [DeviceDto] representing the list of devices.
     */
    @Operation(summary = "List all devices")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            ApiResponse(responseCode = "500", description = "Server error")
        ]
    )
    @GetMapping("/devices")
    suspend fun listDevices(): Flux<DeviceDto>

    /**
     * Retrieves a list of available devices.
     *
     * @return a [Flux] of [DeviceDto] objects representing the available devices.
     */
    @Operation(summary = "List all available devices")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            ApiResponse(responseCode = "500", description = "Server error")
        ]
    )
    @GetMapping("/devices/available")
    suspend fun listAvailableDevices(): Flux<DeviceDto>

    /**
     * Retrieves the details of a specific device.
     *
     * @param id The ID of the device to retrieve.
     * @return A Mono that emits the DeviceDetails object representing the details of the device.
     */
    @Operation(summary = "Get details of a specific device")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Device details retrieved successfully"
            ),
            ApiResponse(responseCode = "404", description = "Device not found"),
            ApiResponse(responseCode = "500", description = "Server error")
        ]
    )
    @GetMapping("/devices/{id}")
    suspend fun getDeviceDetails(@PathVariable id: Long): Mono<DeviceDetails>
}