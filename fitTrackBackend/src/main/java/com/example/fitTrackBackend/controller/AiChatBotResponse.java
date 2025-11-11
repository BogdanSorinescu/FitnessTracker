package com.example.fitTrackBackend.controller;

import com.example.fitTrackBackend.Service.AiChatBotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class AiChatBotResponse {

    private final AiChatBotService aiChatBotService;


    public AiChatBotResponse(AiChatBotService aiChatBotService) {
        this.aiChatBotService = aiChatBotService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody Map<String, String> request){
        String userMessage = request.get("message");
        if(userMessage == null || userMessage.isEmpty()){
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        String response = aiChatBotService.chat(userMessage);
        return ResponseEntity.ok(response);
    }
}
