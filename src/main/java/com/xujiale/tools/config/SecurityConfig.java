package com.xujiale.tools.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

/**
 * @author xujiale 2020/7/14 10:05
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/hello")
                .loginProcessingUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/js/**", "/counter-strike.svg","/hello","/test/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .rememberMe()
                .tokenValiditySeconds(3600 * 24).tokenRepository(new InMemoryTokenRepositoryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin")
                .password(passwordEncoder.encode("123321")).roles("ADMIN");
    }
}
