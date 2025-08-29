package spring.basics.thirdpartyapiintegration.loginapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import spring.basics.thirdpartyapiintegration.loginapi.dtos.FakeStoreLoginRequestDto;
import spring.basics.thirdpartyapiintegration.loginapi.dtos.FakeStoreLoginResponseDto;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    private final String BASE_URL = "https://fakestoreapi.com";
    private final String LOGIN_ENDPOINT = "/auth/login";
    @Override
    public MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        MultiValueMap<String,String> responseHeaders = new LinkedMultiValueMap<>();
        try {
            ResponseEntity<FakeStoreLoginResponseDto> response
                    = restTemplate.postForEntity(BASE_URL + LOGIN_ENDPOINT, fakeStoreLoginRequestDto, FakeStoreLoginResponseDto.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                responseHeaders.add(HttpHeaders.SET_COOKIE, response.getBody().getToken());
                return responseHeaders;
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
