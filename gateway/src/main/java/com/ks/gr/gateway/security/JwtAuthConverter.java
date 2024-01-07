package com.ks.gr.gateway.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.*;

public class JwtAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {
    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, Collection<String>> realmAccess = source.getClaim("realm_access");

        if (realmAccess != null && !realmAccess.isEmpty()) {
            Collection<String> roles = realmAccess.get("roles");
            if (roles != null && !roles.isEmpty()) {
                authorities.addAll(roles.stream().map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role)).toList());
            }
        }
        return Mono.just(new JwtAuthenticationToken(source, authorities));
    }
}
