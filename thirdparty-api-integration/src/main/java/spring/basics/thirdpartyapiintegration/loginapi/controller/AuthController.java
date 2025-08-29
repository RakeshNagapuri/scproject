package spring.basics.thirdpartyapiintegration.loginapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.basics.thirdpartyapiintegration.loginapi.dtos.FakeStoreLoginRequestDto;
import spring.basics.thirdpartyapiintegration.loginapi.services.IAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FakeStoreLoginRequestDto fakeStoreLoginRequestDto) {
        MultiValueMap<String, String> login = authService.login(fakeStoreLoginRequestDto);
        if(login==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(login.toString());

    }
}
