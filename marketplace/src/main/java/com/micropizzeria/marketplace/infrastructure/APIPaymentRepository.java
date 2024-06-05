package com.micropizzeria.marketplace.infrastructure;

import com.micropizzeria.marketplace.domain.model.Order;
import com.micropizzeria.marketplace.domain.model.Payment;
import com.micropizzeria.marketplace.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class APIPaymentRepository implements PaymentRepository {

    @Value("${external.payment-api.url}")
    private String paymentApiUrl;

    @Value("#{T(Boolean).parseBoolean('${feature-toggle.use-payment-api}')}")
    private Boolean useApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean processPayment(Order order) {
        if (!useApiUrl)
            return true;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Payment> request = new HttpEntity<>(order.getPayment(), headers);
        String url = paymentApiUrl + "?orderId=" + order.getUuid();

        return restTemplate.exchange(url, HttpMethod.POST, request, Payment.class).getStatusCode().is2xxSuccessful();
    }
}