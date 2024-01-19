package com.dark.savant.phonebookingservice.repository

import com.dark.savant.phonebookingservice.domain.Booking
import com.dark.savant.phonebookingservice.dto.booking.BookingDto
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Repository
interface BookingRepository : ReactiveCrudRepository<Booking, Long> {

    @Query(
        """
        SELECT EXISTS (
          SELECT 1
          FROM bookings 
          WHERE bookings.device_id = :device_id
            AND bookings.end_time IS NULL
        ) AS hasActiveBooking;
    """
    )
    fun isDeviceBooked(@Param("device_id") deviceId: Long): Mono<Boolean>

    @Query(
        """
        UPDATE bookings
        SET end_time = :end_date_time, updated = :end_date_time
        WHERE device_id = :device_id
          AND end_time IS NULL;
    """
    )
    fun returnDevice(
        @Param("device_id") deviceId: Long,
        @Param("end_date_time") endTime: LocalDateTime
    ): Mono<Unit>

    @Query(
        """
        SELECT 
          b.id AS id,
          b.device_id AS deviceId,
          b.user_id AS bookedById,
          b.created AS startTime,
          b.end_time AS endTime,
          d.manufacturer AS deviceManufacturer,
          d.model AS deviceModel,
          u.login AS bookedBy 
        FROM bookings AS b
        INNER JOIN devices AS d ON b.device_id = d.id
        INNER JOIN users AS u ON b.user_id = u.id
        WHERE b.id = :bookingId;
    """
    )
    fun getBookingDtoById(id: Long): Mono<BookingDto>

    fun findOneByDeviceIdOrderByEndTimeDesc(deviceId: Long): Mono<Booking>
}