package com.example.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {


    @Bean
    PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/access-balance").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/transfer").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/accounts**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/accounts**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/accounts**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/accounts**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/transfer-third-party").hasRole("THIRD-PARTY")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

}