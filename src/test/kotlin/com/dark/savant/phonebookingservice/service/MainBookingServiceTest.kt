package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.domain.Booking
import com.dark.savant.phonebookingservice.repository.BookingRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
internal class MainBookingServiceTest {
    @Mock
    private lateinit var bookingRepository: BookingRepository

    @InjectMocks
    private lateinit var mainBookingService: MainBookingService

    @Test
    fun `bookDevice should return BookingResultDto when device is not booked`() {
        runBlocking {
            val booking = Booking(1L, 1L, 1L)
            val bookingResultDto = booking.toResultDto()
            given(bookingRepository.isDeviceBooked(1L)).willReturn(Mono.just(false))
            given(bookingRepository.save(any())).willReturn(Mono.just(booking))

            val result = mainBookingService.bookDevice(1L, 1L)

            StepVerifier.create(result)
                .assertNext { assertEquals(bookingResultDto, it) }
                .verifyComplete()
        }
    }

    @Test
    fun `returnDevice should return BookingResultDto when device is booked`() {
        runBlocking {
            val booking = Booking(1L, 1L, 1L)
            val bookingResultDto = booking.toResultDto()
            given(bookingRepository.isDeviceBooked(1L)).willReturn(Mono.just(true))
            given(bookingRepository.findByDeviceId(1L)).willReturn(Mono.just(booking))

            val result = mainBookingService.returnDevice(1L)

            StepVerifier.create(result)
                .assertNext { assertEquals(bookingResultDto, it) }
                .verifyComplete()
        }
    }
}