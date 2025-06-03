package com.example.demo.api;

import com.example.demo.entity.Account;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/account")
public class AuthenticationApi {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
          RegisterResponse newAccount = authenticationService.register(registerRequest);
          return ResponseEntity.ok(newAccount);
    }
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
       RegisterResponse newAccount = authenticationService.login(loginRequest);
       return ResponseEntity.ok(newAccount);
    }
    @GetMapping
    public ResponseEntity getAllAccounts() {
        List<Account> accounts = authenticationService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
