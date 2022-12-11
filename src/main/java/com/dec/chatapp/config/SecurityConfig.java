package com.dec.chatapp.config;

import com.dec.chatapp.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    TODO
//    I need to work on this class as it is deprecated and likely to generate an error

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(this.authenticationService);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                .loginPage("/login")
//                .permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//
//        http.formLogin()
//                .defaultSuccessUrl("/home", true);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        http.formLogin()
                .defaultSuccessUrl("/chat", true);

        http.authenticationProvider(this.authenticationService);

        return http.build();
    }


}
