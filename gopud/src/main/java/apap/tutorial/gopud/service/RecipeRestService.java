package apap.tutorial.gopud.service;

import reactor.core.publisher.Mono;

public interface RecipeRestService {
    Mono<String> getStatus(String food);
}
