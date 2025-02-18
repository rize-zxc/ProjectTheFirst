package com.example.buysell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String products(Model model) {
        // Проверяем статус сервера
        if (!serverStatus) {
            // Если сервер недоступен, добавляем сообщение в модель и возвращаем страницу с ошибкой
            model.addAttribute("message", "Сервис временно недоступен. Пожалуйста, попробуйте позже.");
            return "error"; // Возвращаем шаблон error.html
        }
        // Если сервер доступен, возвращаем обычную страницу
        return "products";
    }

    @GetMapping("/status")
    @ResponseBody
    public Map<String, String> checkStatus(@RequestParam(name = "status", required = false) String status) {
        Map<String, String> response = new HashMap<>();

        // Если параметр status передан, обновляем статус сервера
        if (status != null) {
            if ("available".equalsIgnoreCase(status)) {
                serverStatus = true;
            } else if ("unavailable".equalsIgnoreCase(status)) {
                serverStatus = false;
            }
        }

        // Возвращаем сообщение в зависимости от статуса сервера
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