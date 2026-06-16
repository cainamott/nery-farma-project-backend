package com.github.cainamott.nery_farma.impl.client;

import com.github.cainamott.nery_farma.impl.dto.MedicationOrderRequest;
import com.github.cainamott.nery_farma.impl.dto.MedicationOrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Component
public class OrderProcessingClient {

    private final RestClient restClient;

    public OrderProcessingClient(@Value("http://localhost:8089") String baseUrl) {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .build();
    }

    public MedicationOrderResponse sendOrder(MedicationOrderRequest requestDTO) {
        return restClient.post()
                .uri("/medication-orders")
                .body(requestDTO)
                .retrieve()
                .body(MedicationOrderResponse.class);
    }
}