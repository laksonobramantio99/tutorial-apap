package apap.tutorial.gopud.service;

import apap.tutorial.gopud.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecipeRestServiceImpl implements RecipeRestService {
    private final WebClient webClient;

    public RecipeRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.recipeUrl).build();
    }

    @Override
    public Mono<String> getStatus(String food) {
        return this.webClient.get().uri("/recipes/search?excludeIngredients="+food+"&cuisine=german&apiKey="+Setting.apiKey)
                .retrieve().bodyToMono(String.class);
    }
}
