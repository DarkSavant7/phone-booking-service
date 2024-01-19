package com.dark.savant.phonebookingservice.web

import com.dark.savant.phonebookingservice.api.BookingApi
import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import com.dark.savant.phonebookingservice.service.BookingService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BookingController(private val bookingService: BookingService) : BookingApi {

    override suspend fun bookDevice(
        @PathVariable deviceId: Long,
        @PathVariable userId: Long
    ): Mono<BookingResultDto> {
        return bookingService.bookDevice(deviceId, userId)
    }

    override suspend fun returnDevice(@PathVariable deviceId: Long): Mono<BookingResultDto> {
        return bookingService.returnDevice(deviceId)
    }

    override suspend fun bookDeviceByName(
        deviceModel: String,
        deviceManufacturer: String,
        userId: Long
    ): Mono<BookingResultDto> {
        return bookingService.bookDeviceByName(deviceModel, deviceManufacturer, userId)
    }
}