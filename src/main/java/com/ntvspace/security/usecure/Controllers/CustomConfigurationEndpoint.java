package com.ntvspace.security.usecure.Controllers;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@FrameworkEndpoint
@RestController
public class CustomConfigurationEndpoint {

    @Autowired private JWKSet jwkSet;

    @GetMapping("/.well-known/openid-configuration")
    public HashMap<String, String> config() {
        HashMap<String, String> endpoints = new HashMap<String, String>();

        endpoints.put("issuer", "http://localhost:8080");
        endpoints.put("jwks_uri", "http://localhost:8080/.well-known/openid-configuration/jwks");
        endpoints.put("authorization_endpoint", "http://localhost:8080/oauth/authorize");
        endpoints.put("token_endpoint", "http://localhost:8080/oauth/token");
        return  endpoints;
    }

    @GetMapping("/.well-known/openid-configuration/jwks")
    @ResponseBody
    public Map<String, Object> getKey(Principal principal) {
        return jwkSet.toJSONObject();
    }

}
