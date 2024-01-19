package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.domain.Booking
import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import com.dark.savant.phonebookingservice.repository.BookingRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
                logger.error("Device is already booked")
                Mono.error(IllegalStateException("Device is already booked"))
            } else {
                bookingRepository.save(Booking(deviceId = deviceId, userId = userId))
                    .map { savedBooking -> savedBooking.toResultDto() }
            }
        }
    }

    override suspend fun returnDevice(deviceId: Long): Mono<BookingResultDto> {
        return bookingRepository.isDeviceBooked(deviceId).flatMap { isBooked ->
            if (!isBooked) {
                logger.error("Device is not booked")
                Mono.error(IllegalStateException("Device is not booked"))
            } else {
                bookingRepository.returnDevice(deviceId, LocalDateTime.now()).then(
                    bookingRepository.findByDeviceId(deviceId)
                )
                    .map { savedBooking -> savedBooking.toResultDto() }

            }
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MainBookingService::class.java)
    }
}