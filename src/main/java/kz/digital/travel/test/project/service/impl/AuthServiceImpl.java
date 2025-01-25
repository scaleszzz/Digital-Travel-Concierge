package kz.digital.travel.test.project.service.impl;

import kz.digital.travel.test.project.dto.UserLoginRequestDto;
import kz.digital.travel.test.project.dto.UserLoginResponseDto;
import kz.digital.travel.test.project.dto.UserRegistrationRequestDto;
import kz.digital.travel.test.project.entity.Role;
import kz.digital.travel.test.project.entity.User;
import kz.digital.travel.test.project.repository.RoleRepository;
import kz.digital.travel.test.project.repository.UserRepository;
import kz.digital.travel.test.project.security.JwtTokenUtil;
import kz.digital.travel.test.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> register(UserRegistrationRequestDto registrationRequestDto) {
        // Check if user with given email exists
        if (userRepository.existsByEmail(registrationRequestDto.getEmail())) {
            throw new IllegalArgumentException("User with email " + registrationRequestDto.getEmail() + " already exists");
        }

        // Fetch default role ("USER") from the database
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role 'USER' not found"));

        // Create a new user
        User user = User.builder()
                .username(registrationRequestDto.getUsername())
                .email(registrationRequestDto.getEmail())
                .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                .roles(new HashSet<>(Collections.singleton(userRole))) // Add default role
                .build();

        // Save user to database
        User savedUser = userRepository.save(user);

        // Return the saved user wrapped in Optional
        return Optional.of(savedUser);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto loginRequest) {
        // Fetch user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Generate JWT for authenticated user
        var roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        String token = jwtTokenUtil.generateToken(user.getEmail(), roles);

        // Return login response
        return new UserLoginResponseDto(token, "Bearer");
    }
}