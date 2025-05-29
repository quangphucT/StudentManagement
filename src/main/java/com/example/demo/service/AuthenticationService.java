package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.DuplicationException;
import com.example.demo.model.RegisterRequest;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Account register(RegisterRequest registerRequest) {
        Account account =
       try {
           account.setCreatedAt(new Date());
           account.setPassword(passwordEncoder.encode(account.getPassword()));
           Account newAccount =  accountRepository.save(account);
           return newAccount;
       }catch (Exception e) {
           if(e.getMessage().contains(account.getEmail())) {
             throw new DuplicationException("Email already in use");
           }else{
               throw new DuplicationException("Phone already in use");
           }
       }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
