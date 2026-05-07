package br.com.fiap.clyvo_java.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filtrar(HttpSecurity request) throws Exception {

        request
            .csrf(csrf -> csrf.disable())

            .headers(header ->
                header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
            )

            .authorizeHttpRequests(req ->
                req
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
            )

            .formLogin(Customizer.withDefaults());

        return request.build();
    }
}