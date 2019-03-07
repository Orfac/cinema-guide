package ru.kinoguide.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionRestControllerTest {

    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    public SessionRestControllerTest() {
    }


    private ResponseEntity<String> makeSessionAddRequest(HttpEntity httpEntity) throws URISyntaxException {
        URI url = new URI("http://localhost:9090/api/session");
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
    }

    @Test
    public void testIncorrectSessionAddRequest() throws URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Auth-Token", "test");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<String> responseEntity = makeSessionAddRequest(httpEntity);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUnauthorizedSessionAddRequest() throws URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<String> responseEntity = makeSessionAddRequest(httpEntity);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}