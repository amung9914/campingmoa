package com.campingmoa.dev.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                {
                    try {
                        auth
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/error","/status", "/assets/**", "/view/join/**","/view/login/**", "/auth/join/**").permitAll()
                                .requestMatchers("/view/admin").hasRole("ADMIN")
                                .requestMatchers("/view/seller").hasRole("SELLER")
                                .requestMatchers("/view/user").hasRole("USER")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                );
        return http.build();
    }

}
