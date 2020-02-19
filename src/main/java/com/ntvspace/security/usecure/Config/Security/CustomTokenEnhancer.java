package com.ntvspace.security.usecure.Config.Security;

import com.ntvspace.security.usecure.Models.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired private ModelMapper _mapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> additionalInfo = new HashMap<>();
        UserDto user = _mapper.map(authentication.getPrincipal(), UserDto.class);

        additionalInfo.put("name", user.getFirstName() + " " + user.getLastName());
        additionalInfo.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
