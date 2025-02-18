package com.example.buysell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.HashMap;

@Controller
public class ProductController {

    // Переменная для хранения статуса сервера
    private boolean serverStatus = true; // true - сервер доступен, false - недоступен

    @GetMapping("/")
    public String products() {
        return "products";
    }

    @GetMapping("/status")
    @ResponseBody
    public Map<String, String> checkStatus(@RequestParam(name = "status", required = false) String status) {
        Map<String, String> response = new HashMap<>();

        // Если параметр status не передан, возвращаем текущий статус сервера
        if (status == null) {
            response.put("status", serverStatus ? "available" : "unavailable");
            return response;
        }

        // Если параметр status передан, обновляем статус сервера
        if ("available".equalsIgnoreCase(status)) {
            serverStatus = true;
        } else if ("unavailable".equalsIgnoreCase(status)) {
            serverStatus = false;
        }

        // Возвращаем обновленный статус
        response.put("status", serverStatus ? "available" : "unavailable");
        return response;
    }
}