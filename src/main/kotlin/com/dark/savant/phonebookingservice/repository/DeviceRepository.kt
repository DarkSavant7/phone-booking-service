package com.dark.savant.phonebookingservice.repository

import com.dark.savant.phonebookingservice.domain.Device
import com.dark.savant.phonebookingservice.dto.device.DeviceDto
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface DeviceRepository : ReactiveCrudRepository<Device, Long> {
    @Query(
        """
        SELECT d.id AS id, d.manufacturer AS manufacturer, d.model AS model
        FROM devices d
    """
    )
    fun findAllDevicesDto(): Flux<DeviceDto>

    @Query(
        """
        SELECT id AS id, manufacturer AS manufacturer, model AS model
        FROM devices
        WHERE NOT EXISTS (
          SELECT 1 
          FROM bookings 
          WHERE bookings.device_id = devices.id 
            AND bookings.end_time IS NULL
);
    """
    )
    fun findAllAvailableDevicesDto(): Flux<DeviceDto>

    @Query(
        """
        SELECT * FROM devices d
        WHERE d.model = :model AND d.manufacturer = :manufacturer
        AND NOT EXISTS (
        SELECT 1 FROM bookings b 
        WHERE b.device_id = d.id
        AND b.end_time IS NULL
        )
        LIMIT 1
    """
    )
    fun getOneUnbooketDeviceByManufacturerAndModel(
        @Param("model") model: String,
        @Param("manufacturer") manufacturer: String
    ): Mono<Device>
}
