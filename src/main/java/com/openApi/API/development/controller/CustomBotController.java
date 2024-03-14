package com.openApi.API.development.controller;

import com.openApi.API.development.dto.ChatGptRequest;
import com.openApi.API.development.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class CustomBotController {


    private String model="gpt-3.5-turbo";

    @Value(("${openai.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/chat")
    public ResponseEntity<ChatGptResponse>  chat(@RequestParam String prompt){
        ChatGptRequest request=new ChatGptRequest(model,prompt);
        System.out.println(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(apiURL, request, ChatGptResponse.class));
    }

}
