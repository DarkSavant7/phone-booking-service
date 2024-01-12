package com.dark.savant.phonebookingservice.api

import com.dark.savant.phonebookingservice.dto.booking.BookingResultDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import reactor.core.publisher.Mono

/**
 * Interface for the BookingApiController.
 */
interface BookingApi {

    /**
     * Books a device for a user.
     *
     * @param deviceId The ID of the device to be booked.
     * @param userId The ID of the user who is booking the device. @TODO Used as long as Security is not implemented
     * @return A Mono that emits a BookingResultDto object which contains the booking information.
     */
    @Operation(summary = "Book device")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful operation"),
            ApiResponse(responseCode = "400", description = "Invalid Input"),
            ApiResponse(responseCode = "404", description = "Device or user not found")
        ]
    )
    @PostMapping("/bookings/{deviceId}/{userId}")
    suspend fun bookDevice(
        @PathVariable deviceId: Long,
        @PathVariable userId: Long
    ): Mono<BookingResultDto>

    /**
     * Return device.
     *
     * @param deviceId The ID of the device to be returned.
     *
     * @return A mono emitting an instance of BookingResultDto representing the booking result.
     */
    @Operation(summary = "Return device")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful operation"),
            ApiResponse(responseCode = "400", description = "Invalid Input"),
            ApiResponse(responseCode = "404", description = "Device not found")
        ]
    )
    @PutMapping("/bookings/{deviceId}")
    suspend fun returnDevice(@PathVariable deviceId: Long): Mono<BookingResultDto>
}