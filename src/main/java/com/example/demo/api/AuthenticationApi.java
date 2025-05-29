package com.example.demo.api;

import com.example.demo.entity.Account;
import com.example.demo.model.RegisterRequest;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/account")
public class AuthenticationApi {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping()
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
          Account newAccount = authenticationService.register(registerRequest);
          return ResponseEntity.ok(newAccount);
    }

}
