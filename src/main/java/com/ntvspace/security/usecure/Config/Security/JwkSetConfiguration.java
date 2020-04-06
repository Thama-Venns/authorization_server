package com.ntvspace.security.usecure.Config.Security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Order(1)
public class JwkSetConfiguration extends AuthorizationServerSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
            .mvcMatchers("/well-known/jwk.json")
            .and()
            .authorizeRequests()
            .mvcMatchers("/well-known/jwk.json")
            .permitAll();
    }

    @Bean
    public KeyPair keyPair() {
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("jwk.jks"), "password".toCharArray())
                .getKeyPair("ntvspace");
        return keyPair;
    }

    @Bean
    public JWKSet jwkSet() {

        RSAPublicKey publicKey = (RSAPublicKey) keyPair().getPublic();

        RSAKey.Builder builder = new RSAKey.Builder(publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .keyID("ABCD123EFG")
                .algorithm(JWSAlgorithm.RS256);

        JWKSet jwkSet = new JWKSet(builder.build());
        return jwkSet;
    }
}
