package com.dark.savant.phonebookingservice.web

import com.dark.savant.phonebookingservice.dto.details.Bands
import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.dto.details.SubModelSpecification
import com.dark.savant.phonebookingservice.service.DeviceService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.BDDMockito.given
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

//@ExtendWith(MockitoExtension::class)
//@WebFluxTest(DeviceController::class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeviceControllerTest {

    @MockBean
    private lateinit var deviceService: DeviceService
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `test listDevices`() {
        runBlocking {
            given(deviceService.listDevices()).willReturn(Flux.just())

            webTestClient.get().uri("/devices")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
        }
    }

    @Test
    fun `test listAvailableDevices`() {
        runBlocking {
            given(deviceService.listAvailableDevices()).willReturn(Flux.just())

            webTestClient.get().uri("/devices/available")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
        }
    }

    @Test
    fun `test getDeviceDetails`() {
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
            given(deviceService.getDeviceDetails(14L)).willReturn(Mono.just(deviceDetails))

            webTestClient.get().uri("/devices/14")
                .exchange()
                .expectStatus().isOk
        }
    }

    companion object {
        @Container
        private val container = PostgreSQLContainer<Nothing>("postgres:latest").apply {
            withDatabaseName("phone_booking_service")
            withUsername("postgres")
            withPassword("postgres")
        }

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url") { container.jdbcUrl.replace("jdbc", "r2dbc") }
            registry.add("spring.r2dbc.password", container::getPassword)
            registry.add("spring.r2dbc.username", container::getUsername)
        }
    }
}