package com.bgpark.nearlog.api.config;

import com.bgpark.nearlog.api.dto.UserDto;
import com.bgpark.nearlog.common.domain.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.HashMap;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication);
        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        System.out.println(principal);
        System.out.println(credentials);
        User user = new User("123", credentials, "token");
//        CustomAuthenticationToken token = new CustomAuthenticationToken(principal, credentials, authentication.getAuthorities());
//        System.out.println(token);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("key","123");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, credentials, authentication.getAuthorities());
        return token;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
