package ma.enset.mabbdcc.controllers;

import ma.enset.mabbdcc.outputs.TomModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModalController {
    private ChatClient chatClient;
    @Value("classpath:images/14d8088694289d18a2a9b53955a3dfb5.jpg")

    private Resource imageResource;
    public MultiModalController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    @GetMapping("/describeImage")
    public TomModel describeImage(String imageUrl) {
        return chatClient.prompt()
                .system("Vous êtes un expert en analyse d'images, décrivez l'image fournie.")
                .user(u->
                        u.text("Decrirez l'image suivante")
                        .media(MediaType.IMAGE_JPEG, imageResource))
                .call()
                .entity(TomModel.class);
    }
}
