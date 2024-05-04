package com.base64.gamesback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MainSecurity  {

    private final String[] ROUTES_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/register",
            "/auth/login",
            "/security/speciality/**",
            "/security/hearing_loss/**"
    };

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, CorsConfig corsConfig) throws  Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                        .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(ROUTES_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                .anyRequest().permitAll());
        return http.build();
    }

}
