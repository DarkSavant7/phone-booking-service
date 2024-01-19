package com.dark.savant.phonebookingservice.service

import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import reactor.core.publisher.Mono

/**
 * Interface for a booking service.
 */
interface BookingService {
    /**
     * Books a device for a user.
     *
     * @param deviceId The ID of the device to be booked.
     * @param userId The ID of the user who is booking the device.
     * @return A Mono that emits a BookingResultDto object which contains the booking information.
     */
    suspend fun bookDevice(deviceId: Long, userId: Long): Mono<BookingResultDto>


    suspend fun bookDeviceByName(
        deviceModel: String,
        deviceManufacturer: String,
        userId: Long
    ): Mono<BookingResultDto>

    /**
     * Returns a Mono emitting an instance of BookingResultDto representing the booking result for the device with the given ID.
     *
     * @param id The ID of the device to be returned.
     * @return A Mono emitting an instance of BookingResultDto representing the booking result.
     */
    suspend fun returnDevice(deviceId: Long): Mono<BookingResultDto>

}