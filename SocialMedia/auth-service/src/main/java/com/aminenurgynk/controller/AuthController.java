package com.aminenurgynk.controller;


import com.aminenurgynk.dto.request.ActivateRequestDto;
import com.aminenurgynk.dto.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import com.aminenurgynk.dto.request.RegisterRequestDto;
import com.aminenurgynk.dto.response.RegisterResponseDto;
import com.aminenurgynk.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.aminenurgynk.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto){

        return ResponseEntity.ok(authService.register(dto));

    }

    @PostMapping(LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping(ACTIVATION)
    public ResponseEntity<Boolean> login(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }


}
