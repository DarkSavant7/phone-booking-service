package com.dark.savant.phonebookingservice.web

import com.dark.savant.phonebookingservice.api.DeviceApi
import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import com.dark.savant.phonebookingservice.service.DeviceService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class DeviceController(private val deviceService: DeviceService) : DeviceApi {

    override suspend fun listDevices(): Flux<DeviceDto> {
        return deviceService.listDevices()
    }

    override suspend fun listAvailableDevices(): Flux<DeviceDto> {
        return deviceService.listAvailableDevices()
    }

    override suspend fun getDeviceDetails(@PathVariable id: Long): Mono<DeviceDetails> {
        return deviceService.getDeviceDetails(id)
    }
}