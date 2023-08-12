package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import registrationsystem.api.dto.LoginDTO;
import registrationsystem.api.dto.response.LoginResponseDTO;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.security.JwtTokenService;
import registrationsystem.api.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){

        var authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) userService.loadUserByUsername(loginDTO.getUsername());
        String jwt = jwtTokenService.generateToken(user);
        LoginResponseDTO loginResponseDTO = modelMapper.map(user, LoginResponseDTO.class);
        loginResponseDTO.setToken(jwt);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
