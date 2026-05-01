package com.servlet.recipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/get-recipe")
    public ResponseEntity<String> getRecipe(@RequestParam String ingredients,
                                            @RequestParam String cuisine,
                                            @RequestParam String dietaryRestrictions){
        return ResponseEntity.ok(recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions));
    }
}
