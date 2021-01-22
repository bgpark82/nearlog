package com.bgpark.nearlog.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] SWAGGER_MATCHER = {"/swagger-ui.html","/swagger-resources/**","/configuration/ui","/swagger-resources/**","/configuration/security","/webjars/**","/v2/api-docs"};

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                    .and()
                .cors()
                    .and()
                .authorizeRequests()
                .antMatchers(SWAGGER_MATCHER).permitAll()
                .antMatchers("/oauth/**","/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET).access("#oauth2.hasAnyScope('read')");

    }

}
