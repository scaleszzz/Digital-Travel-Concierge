package kz.digital.travel.test.project.controller;

import kz.digital.travel.test.project.dto.UserLoginRequestDto;
import kz.digital.travel.test.project.dto.UserLoginResponseDto;
import kz.digital.travel.test.project.dto.UserRegistrationRequestDto;
import kz.digital.travel.test.project.entity.User;
import kz.digital.travel.test.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationRequestDto registrationRequestDto) {
        Optional<User> user = authService.register(registrationRequestDto);
        return user.map(u -> ResponseEntity.status(201).body(u))
                .orElseGet(() -> ResponseEntity.status(400).body(null));
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}