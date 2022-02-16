package com.example.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

import static com.example.securitydemo.security.ApplicationRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/","/index.html")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/students/**").hasAuthority(ApplicationPermission.STUDENT_READ.name())
                .anyRequest()
                .authenticated()
                .and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(Arrays.asList(
                User.builder().username("harish").password(encoder.encode("password")).roles(STUDENT.name()).authorities(STUDENT.getAuthorities()).build(),
                User.builder().username("manish").password(encoder.encode("password")).roles(STUDENT.name()).authorities(STUDENT.getAuthorities()).build(),
                User.builder().username("kartik").password(encoder.encode("password")).roles(ADMIN.name(),STUDENT.name()).authorities(ADMIN.getAuthorities()).build()
        ));
    }
}
