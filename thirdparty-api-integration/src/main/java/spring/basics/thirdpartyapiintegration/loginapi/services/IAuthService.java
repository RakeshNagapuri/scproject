package spring.basics.thirdpartyapiintegration.loginapi.services;

import org.springframework.util.MultiValueMap;
import spring.basics.thirdpartyapiintegration.loginapi.dtos.FakeStoreLoginRequestDto;

public interface IAuthService {
    MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto);
}
