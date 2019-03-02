package ru.kinoguide.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenGeneratorConfiguration {

    @Bean("tokenGenerator")
    public TokenGenerator getSecureTokenGenerator() {
        return new SecureRandomString(64);
    }
}
