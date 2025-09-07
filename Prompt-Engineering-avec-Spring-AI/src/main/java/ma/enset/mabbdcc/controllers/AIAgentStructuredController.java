package ma.enset.mabbdcc.controllers;

import ma.enset.mabbdcc.outputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;

public class AIAgentStructuredController {

        private ChatClient chatClient;


        public AIAgentStructuredController(ChatClient.Builder chatClient) {
            this.chatClient = chatClient
                    .defaultAdvisors(new SimpleLoggerAdvisor())
                    .build();
        }

        @GetMapping("/askAgent")

        public MovieList askLLM(String query) {
            return chatClient.prompt()
                    .system("Vous etes un spécialiste du cinéma, vous répondez aux questions de l'utilisateur sur les films.")
                    .user(query)
                    .call()
                    .entity(MovieList.class); // Assuming Movie is a record or class that represents the structured output
        }
}

