package com.example.buysell.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController // Заменяем @Controller на @RestController
public class ProductController {

    // Переменная для хранения статуса сервера
    private boolean serverStatus = true; // true - сервер доступен, false - недоступен

    @GetMapping("/")
    public String products() {
        // Проверяем статус сервера
        if (!serverStatus) {
            // Если сервер недоступен, возвращаем сообщение о недоступности
            return "Сервис временно недоступен. Пожалуйста, попробуйте позже.";
        }
        // Если сервер доступен, возвращаем обычное сообщение
        return "Посмотри, сегодня звезды ярче чем вчера\n" +
                "Оставь дела, пойдем скорей на свежий воздух\n" +
                "Посмотри, все несерьезно, все это игра\n" +
                "Никто не прав, и все попытки, если честно, бесполезны\n" +
                "Посмотри, мой друг, я спекся, мой окончен бой\n" +
                "Но не грусти, ведь все случается так просто\n" +
                "Без меня все будет также, также как со мной\n" +
                "Осколки звезд сорвутся с неба и исчезнут бесполезно\n" +
                "Над нами полночь, она безмолвна\n" +
                "Но она навсегда-навсегда прощает нас\n" +
                "В этих темных дворах навсегда теряет нас\n" +
                "Плывут над городами, легко прощаясь с нами\n" +
                "Обрывки туч по ветру, но мой друг ответь мне\n" +
                "Во тьме утонет берег, и ты боишься верить\n" +
                "Не бойся — это глупо, скоро тьма отступит\n" +
                "Затем исчезнут страны, убийцы и тираны\n" +
                "Границы станут былью, стены станут пылью\n" +
                "И вдруг сорвется слово, и мы полюбим снова\n" +
                "Еще сильней и чище, но мой друг ты слышишь\n" +
                "В дали раскаты грома, и мы должны быть дома\n" +
                "Не бойся — это глупо, скоро тьма отступит";
    }

    @GetMapping("/status")
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