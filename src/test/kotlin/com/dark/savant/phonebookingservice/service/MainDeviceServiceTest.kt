package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.client.PhonesApiClient
import com.dark.savant.phonebookingservice.domain.Device
import com.dark.savant.phonebookingservice.dto.details.Bands
import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.details.SubModelSpecification
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import com.dark.savant.phonebookingservice.repository.DeviceRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootTest
@ExtendWith(SpringExtension::class)
class DeviceServiceTest {

    @InjectMocks
    lateinit var mainDeviceService: MainDeviceService

    @Mock
    lateinit var deviceRepository: DeviceRepository

    @Mock
    lateinit var phonesApiClient: PhonesApiClient

    @Test
    fun testListDevices() {
        runBlocking {
            `when`(deviceRepository.findAllDevicesDto()).thenReturn(
                Flux.just(
                    DeviceDto(
                        1,
                        "manufacturer",
                        "model"
                    )
                )
            )
            val result = mainDeviceService.listDevices().collectList().block()
            assertNotNull(result)
            assertEquals(1, result?.size)
        }
    }

    @Test
    fun testListAvailableDevices() {
        runBlocking {
            `when`(deviceRepository.findAllAvailableDevicesDto()).thenReturn(
                Flux.just(
                    DeviceDto(
                        1,
                        "manufacturer",
                        "model"
                    )
                )
            )
            val result = mainDeviceService.listAvailableDevices().collectList().block()
            assertNotNull(result)
            assertEquals(1, result?.size)
        }
    }

    @Test
    fun testGetDeviceDetails() {

        runBlocking {
            val bands = Bands(
                `2g` = listOf("2G Band 1", "2G Band 2"),
                `3g` = listOf("3G Band 1", "3G Band 2"),
                `4g` = listOf("4G Band 1", "4G Band 2"),
                `5g` = listOf("5G Band 1", "5G Band 2")
            )

            val subModelSpecification = SubModelSpecification(
                processor = "Apple A14 Bionic",
                ram = "4 GB",
                videoChip = "Apple GPU",
                screenResolution = "1170 x 2532 pixels",
                screenSize = "6.1 inches",
                batterySize = "2815 mAh"
            )

            val deviceDetails = DeviceDetails(
                manufacturer = "Apple",
                model = "iPhone 12",
                bands = bands,
                weight = "164 grams",
                description = "Apple iPhone 12 is the successor of the iPhone 11. It comes with 5G support, A14 Bionic processor, and improved cameras.",
                subModelSpecification = subModelSpecification
            )
            `when`(deviceRepository.findById(anyLong())).thenReturn(
                Mono.just(
                    Device(
                        1,
                        "manufacturer",
                        "model"
                    )
                )
            )
            `when`(phonesApiClient.getPhoneInfo(anyString(), anyString())).thenReturn(
                Mono.just(
                    deviceDetails
                )
            )
            val result = mainDeviceService.getDeviceDetails(anyLong()).block()
            assertNotNull(result)
            assertEquals(deviceDetails.model, result?.model)
        }
    }
}