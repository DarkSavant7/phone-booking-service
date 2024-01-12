package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Represents a service for managing devices.
 */
interface DeviceService {
    /**
     * Retrieves a list of devices.
     *
     * @return A [Flux] of [DeviceDto] representing the list of devices.
     */
    suspend fun listDevices(): Flux<DeviceDto>

    /**
     * Lists all available devices.
     *
     * @return A Flux of DeviceDto objects representing the available devices.
     */
    suspend fun listAvailableDevices(): Flux<DeviceDto>

    /**
     * Retrieves the details of a specific device.
     *
     * @param id The ID of the device to retrieve.
     * @return A Mono that emits the DeviceDetails object representing the details of the device.
     */
    suspend fun getDeviceDetails(id: Long): Mono<DeviceDetails>
}