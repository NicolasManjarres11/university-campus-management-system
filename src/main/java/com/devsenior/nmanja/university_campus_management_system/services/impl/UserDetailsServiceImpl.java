package com.devsenior.nmanja.university_campus_management_system.services.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        var user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("No se encontró el username '"+username+"'."));

        var authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role)) //Convierte cada rol traido de la base de datos a Authority
            .toList();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
    
    
}
