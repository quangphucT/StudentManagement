package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.DuplicationException;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest registerRequest) {
         Account acc = modelMapper.map(registerRequest, Account.class);
       try {
           acc.setCreatedAt(new Date());
           acc.setPassword(passwordEncoder.encode(acc.getPassword()));
           Account newAccount =  accountRepository.save(acc);
           return modelMapper.map(newAccount, RegisterResponse.class);
       }catch (Exception e) {
           if(e.getMessage().contains(acc.getEmail())) {
             throw new DuplicationException("Email already in use");
           }else{
               throw new DuplicationException("Phone already in use");
           }
       }

    }
    public RegisterResponse login(LoginRequest loginRequest) {
       try {
          Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                   loginRequest.getEmail(),
                   loginRequest.getPassword()
           ));
          Account account  = (Account) authentication.getPrincipal();
          return modelMapper.map(account, RegisterResponse.class);
       }catch (Exception e) {
           throw new EntityNotFoundException("Invalid email or password");
       }
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findAccountByEmail(email);
    }
}
