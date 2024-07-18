package com.candido.trilhaBackEndJR_JUN15.controller;

import com.candido.trilhaBackEndJR_JUN15.entity.user.User;
import com.candido.trilhaBackEndJR_JUN15.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.candido.trilhaBackEndJR_JUN15.security.TokenJWT;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/login")
public class UserLoginController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public UserLoginController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWT> login(@RequestBody User user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
