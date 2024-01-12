package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.domain.Booking
import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import com.dark.savant.phonebookingservice.repository.BookingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class MainBookingService(
    private val bookingRepository: BookingRepository,
) : BookingService {

    @Transactional
    override suspend fun bookDevice(deviceId: Long, userId: Long): Mono<BookingResultDto> {
        return bookingRepository.isDeviceBooked(deviceId).flatMap { isBooked ->
            if (isBooked) {
                Mono.error<BookingResultDto>(IllegalStateException("Device is already booked"))
            } else {
                val booking = Booking(deviceId = deviceId, userId = userId)
                bookingRepository.save(booking).map { savedBooking -> savedBooking.toResultDto() }
            }
        }
    }

    override suspend fun returnDevice(deviceId: Long): Mono<BookingResultDto> {
        return bookingRepository.isDeviceBooked(deviceId).flatMap { isBooked ->
            if (!isBooked) {
                Mono.error<BookingResultDto>(IllegalStateException("Device is not booked"))
            } else {
                bookingRepository.returnDevice(deviceId, LocalDateTime.now())
                bookingRepository.findByDeviceId(deviceId)
                    .map { savedBooking -> savedBooking.toResultDto() }
            }
        }
    }
}