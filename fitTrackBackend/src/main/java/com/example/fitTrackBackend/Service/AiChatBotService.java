package com.example.fitTrackBackend.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatBotService {

    private final ChatClient chatClient;

        public AiChatBotService(ChatClient.Builder builder){
            chatClient = builder.build();
        }


        public String chat(String prompt){

           String fitnessPrompt = "You are a fitness assistant. Only answer questions about workout splits and nutrition when it comes to bulking or cutting and end every response with motivation and include the saying: 'Yeah buddy!' in them at the end sometimes. "+
                   "if the question is unrelated,respond with: 'I can only answer fitness related questions.'\n"+
                   prompt;
           return chatClient
                   .prompt(prompt)
                   .call()
                   .content();
        }


}
