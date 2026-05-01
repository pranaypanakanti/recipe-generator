package com.servlet.recipegenerator;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {

    private final ChatClient chatClient;

    public RecipeService(GoogleGenAiChatModel chatModel){
        this.chatClient = ChatClient.create(chatModel);
    }

    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestrictions){
        var templet = """
                I want to create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is {cuisine}.
                Consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide me with a detailed recipy including title, list of ingredients, and cooking instructions.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(templet);

        Map<String, Object> params = Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions",dietaryRestrictions
        );

        String prompt = promptTemplate.render(params);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }


}
