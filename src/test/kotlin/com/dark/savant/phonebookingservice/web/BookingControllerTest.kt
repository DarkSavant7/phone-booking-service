package com.dark.savant.phonebookingservice.web

import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import com.dark.savant.phonebookingservice.service.BookingService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class BookingControllerTest {
    @InjectMocks
    private lateinit var bookingController: BookingController

    @Mock
    private lateinit var bookingService: BookingService

    @Test
    fun bookDevice() {
        runBlocking {
            given(
                bookingService.bookDevice(
                    ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()
                )
            )
                .willReturn(
                    Mono.just(
                        BookingResultDto(
                            id = 1L,
                            deviceId = 101L,
                            bookedById = 1L,
                            startTime = LocalDateTime.of(2023, 1, 1, 0, 0)
                        )
                    )
                )

            val result = bookingController.bookDevice(1, 1)

            StepVerifier.create(result)
                .expectNextMatches { it != null }
                .verifyComplete()
        }
    }

    @Test
    fun returnDevice() {
        runBlocking {
            given(
                bookingService.returnDevice(
                    ArgumentMatchers.anyLong()
                )
            )
                .willReturn(
                    Mono.just(
                        BookingResultDto(
                            id = 1L,
                            deviceId = 101L,
                            bookedById = 1L,
                            startTime = LocalDateTime.of(2023, 1, 1, 0, 0)
                        )
                    )
                )

            val result = bookingController.returnDevice(1)

            StepVerifier.create(result)
                .expectNextMatches { it != null }
                .verifyComplete()
        }
    }
}