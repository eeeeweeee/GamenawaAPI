package com.gamenawa.eeeeweeee.global.util.json;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCall {

    @Value("${spring.secret.openCritic.url}")
    private String OPEN_CRITIC_URL;

    @Value("${spring.secret.openCritic.key}")
    private String OPEN_CRITIC_API_KEY;

    @Value("${spring.secret.openCritic.host}")
    private String OPEN_CRITIC_HOST;

    private final RestTemplate restTemplate;

    private ApiCall() {
        restTemplate = new RestTemplate();
    }

    private HttpEntity<String> setHeaderForOpenCriticRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", OPEN_CRITIC_API_KEY);
        headers.set("X-RapidAPI-Host", OPEN_CRITIC_HOST);

        return new HttpEntity<>("parameters", headers);
    }

    public ResponseEntity<String> getGameInfoFromOpenCritic(String title) {
        String url = OPEN_CRITIC_URL + "search?criteria=" + title;
        HttpEntity<String> requestEntity = setHeaderForOpenCriticRequest();

        return restTemplate.exchange(url , HttpMethod.GET, requestEntity, String.class);
    }

    public ResponseEntity<String> getGameScoreFromOpenCritic(int gameId) {
        String url = OPEN_CRITIC_URL + gameId;
        HttpEntity<String> requestEntity = setHeaderForOpenCriticRequest();

        return restTemplate.exchange(url , HttpMethod.GET, requestEntity, String.class);
    }
}
