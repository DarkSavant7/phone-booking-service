package com.dark.savant.phonebookingservice.client

import com.dark.savant.phonebookingservice.config.properties.AppProperties
import com.dark.savant.phonebookingservice.dto.details.DeviceDetails
import com.dark.savant.phonebookingservice.service.MainBookingService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class PhonesApiClient(private val appProperties: AppProperties) {
    private val webClient: WebClient =
        WebClient.builder().baseUrl(appProperties.detailsService.host).build()

    fun getPhoneInfo(manufacturer: String, model: String): Mono<DeviceDetails> {
        logger.info("Uri would be: ${appProperties.detailsService.url}manufacturer=$manufacturer&model=$model")
        return webClient.get()
            .uri("${appProperties.detailsService.url}manufacturer=$manufacturer&model=$model")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(DeviceDetails::class.java)
//            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MainBookingService::class.java)
    }
}