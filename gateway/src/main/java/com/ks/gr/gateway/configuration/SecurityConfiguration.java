package com.ks.gr.gateway.configuration;

import com.ks.gr.gateway.security.JwtAuthConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class SecurityConfiguration {

    @Value("${application.jwt.jwks-uri}")
    private String jwksUri;

    @Bean
    @SneakyThrows
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers(HttpMethod.GET, "/articles/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/users/**").permitAll()
                        .anyExchange().authenticated())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec
                                .jwkSetUri(jwksUri)
                                .jwtAuthenticationConverter(new JwtAuthConverter())));

        return http.build();
    }
}
