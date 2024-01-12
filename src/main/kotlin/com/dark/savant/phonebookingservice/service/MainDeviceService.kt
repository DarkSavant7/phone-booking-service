package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.client.PhonesApiClient
import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import com.dark.savant.phonebookingservice.repository.DeviceRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MainDeviceService(
    private val deviceRepository: DeviceRepository,
    private val phonesApiClient: PhonesApiClient
) : DeviceService {

    override suspend fun listDevices(): Flux<DeviceDto> {
        return deviceRepository.findAllDevicesDto()
    }

    override suspend fun listAvailableDevices(): Flux<DeviceDto> {
        return deviceRepository.findAllAvailableDevicesDto()
    }

    override suspend fun getDeviceDetails(id: Long): Mono<DeviceDetails> =
        deviceRepository.findById(id).flatMap { device ->
            phonesApiClient.getPhoneInfo(
                manufacturer = device.manufacturer.replace(" ", "_"),
                model = device.model.replace(" ", "_")
            )
        }

}