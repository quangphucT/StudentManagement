package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.DuplicationException;
import com.example.demo.model.RegisterRequest;
import com.example.demo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    ModelMapper modelMapper;
    public Account register(RegisterRequest registerRequest) {
         Account acc = modelMapper.map(registerRequest, Account.class);
       try {
           acc.setCreatedAt(new Date());
           acc.setPassword(passwordEncoder.encode(acc.getPassword()));
           Account newAccount =  accountRepository.save(acc);
           return newAccount;
       }catch (Exception e) {
           if(e.getMessage().contains(acc.getEmail())) {
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
