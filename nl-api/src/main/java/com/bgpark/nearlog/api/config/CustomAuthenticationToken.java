package com.bgpark.nearlog.api.config;

import com.bgpark.nearlog.common.domain.User;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@ToString
public class CustomAuthenticationToken extends AbstractAuthenticationToken {


    private String principal;
    private String credentials;

    public CustomAuthenticationToken(String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean isAuthenticated() {
        return super.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        super.setAuthenticated(true);
    }

    @Override
    public Object getDetails() {
        return super.getDetails();
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
