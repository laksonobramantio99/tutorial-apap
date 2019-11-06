package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.service.RecipeRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeRestController {
    @Autowired
    RecipeRestService recipeRestService;

    @GetMapping(value = "/recipe/excludeIngredient={food}")
    private Mono<String> getRecipe(@PathVariable String food) {
        return recipeRestService.getStatus(food);
    }
}
