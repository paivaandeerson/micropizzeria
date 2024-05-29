package com.micropizzeria.marketplace.infrastructure;

import com.micropizzeria.marketplace.domain.model.Order;
import com.micropizzeria.marketplace.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class APIPaymentRepository implements PaymentRepository {

    @Value("${external.payment-api.url}")
    private String paymentApiUrl;

    @Value("${feature-toggle.use-payment-api}")
    private Boolean useApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean processPayment(Order order) {

        String url = paymentApiUrl + "?orderId=" + order.getUuid();
        if (useApiUrl)
            return restTemplate.getForEntity(url, String.class).getStatusCode().is2xxSuccessful();
        else
            return true;
    }
}