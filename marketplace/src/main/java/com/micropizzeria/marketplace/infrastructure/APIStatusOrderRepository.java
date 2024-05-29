package com.micropizzeria.marketplace.infrastructure;


import com.micropizzeria.marketplace.domain.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class APIStatusOrderRepository implements StatusOrderRepository {

    @Value("${external.statusorder-api.url}")
    private String statusApiUrl;

    @Value("${feature-toggle.use-status-order-api}")
    private Boolean useApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getStatus(UUID orderId) {

        String url = statusApiUrl + "?orderId=" + orderId;
        if (useApiUrl)
            return restTemplate.getForObject(url, String.class);
        else
            return "Pedido entregue a cozinha";
    }
}