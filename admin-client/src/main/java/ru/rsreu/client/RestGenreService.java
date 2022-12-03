package ru.rsreu.client;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

public class RestGenreService implements GenreService {

    private final RestTemplate restTemplate;

    public RestGenreService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<Genre> findAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                "http://localhost:8080/api/genres",
                Genre[].class)));
    }

    @Override
    public Genre addIngredient(Genre genre) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/genres",
                genre,
                Genre.class);
    }

    private ClientHttpRequestInterceptor
    getBearerTokenInterceptor(String accessToken) {
        return (request, bytes, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, bytes);
        };
    }
}
