package com.bgpark.nearlog.api.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserDto {


    @ToString
    @NoArgsConstructor
    public static class Detail implements UserDetails {

        private String username;
        private String password;
        private String authority;

        public Detail(String username, String password, String authority) {
            this.username = username;
            this.password = password;
            this.authority = authority;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Arrays.asList(new SimpleGrantedAuthority(authority));
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
