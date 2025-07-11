package com.devsenior.nmanja.university_campus_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter authenticationFilter) throws Exception{

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(authz -> authz

            //Acceso a los métodos HTTP para la parte de estudiantes

            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/v3/**").permitAll()
            .requestMatchers("/authenticate").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/campus/students").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/campus/students/**").hasAnyRole("ADMIN","STUDENT")
            .requestMatchers(HttpMethod.POST, "/api/campus/students").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/campus/students/**").hasAnyRole("ADMIN","STUDENT")
            .requestMatchers(HttpMethod.DELETE, "/api/campus/students/**").hasRole("ADMIN")

            //Acceso a los métodos HTTP para la parte de estudiantes

            .requestMatchers(HttpMethod.GET, "/api/campus/courses").hasAnyRole("ADMIN","STUDENT")
            .requestMatchers(HttpMethod.GET, "/api/campus/courses/**").hasAnyRole("ADMIN","STUDENT")
            .requestMatchers(HttpMethod.POST, "/api/campus/courses").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/campus/courses/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/campus/courses/**").hasRole("ADMIN")

            //Acceso a los métodos HTTP para la parte de profesores

            .requestMatchers("/api/campus/professors").hasRole("ADMIN")
            .requestMatchers("/api/campus/professors/**").hasRole("ADMIN")

            //Acceso a los métodos HTTP para la parte de inscripcions

            .requestMatchers(HttpMethod.GET, "/api/campus/enrollments").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/campus/enrollments/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/campus/enrollments").hasAnyRole("ADMIN","STUDENT")
            .requestMatchers(HttpMethod.PUT, "/api/campus/enrollments/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/campus/enrollments/**").hasAnyRole("ADMIN","STUDENT")



                    .anyRequest().authenticated())
                .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
                    
        return http.build();


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    
}
