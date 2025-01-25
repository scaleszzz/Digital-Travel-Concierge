package kz.digital.travel.test.project.service;

import kz.digital.travel.test.project.dto.UserLoginRequestDto;
import kz.digital.travel.test.project.dto.UserLoginResponseDto;
import kz.digital.travel.test.project.dto.UserRegistrationRequestDto;
import kz.digital.travel.test.project.entity.User;

import java.util.Optional;

public interface AuthService {

    Optional<User> register(UserRegistrationRequestDto registrationRequestDto);
    UserLoginResponseDto login(UserLoginRequestDto loginRequestDto);
}