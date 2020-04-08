package com.topcoder.calculatorbot.api;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class AuthController {

  private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

  private static final String BASE_URL = "https://slack.com/api/oauth.v2.access";

  @Value("${clientId}")
  private String clientId;

  @Value("${clientSecret}")
  private String clientSecret;

  @Value("${redirectUrl}")
  private String redirectUrl;

  @Autowired
  private WebClient.Builder webClient;

  @GetMapping("/auth")
  public ResponseEntity authorise(@RequestParam String code) {
    LOG.info("Authorising user");
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());

    MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
    params.add("code", code);
    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("redirect_url", redirectUrl);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

    ResponseEntity<SlackAuthResponse> res = restTemplate.postForEntity(BASE_URL, request, SlackAuthResponse.class);
    return res;
  }



}