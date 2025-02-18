package com.example.buysell.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private boolean serverStatus = true;

    public boolean isServerAvailable() {
        return serverStatus;
    }

    public Map<String, String> updateAndGetStatus(String status) {
        if (status != null) {
            if ("available".equalsIgnoreCase(status)) {
                serverStatus = true;
            } else if ("unavailable".equalsIgnoreCase(status)) {
                serverStatus = false;
            }
        }

        Map<String, String> response = new HashMap<>();
        if (serverStatus) {
            response.put("status", "available");
            response.put("message", "Сервис работает в штатном режиме.");
        } else {
            response.put("status", "unavailable");
            response.put("message", "Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }

        return response;
    }
}