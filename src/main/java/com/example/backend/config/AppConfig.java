package com.example.backend.config;

import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css","/images").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/bike/all").permitAll()
                .antMatchers("/bike/edit/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/bike/save-edit/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/bike/show-bike-form/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/bike/save/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/bike/delete/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/spare/all").permitAll()
                .antMatchers("/spare/edit/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/spare/save-edit/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/spare/spare-form/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/spare/save/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/spare/delete/**").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/bike/all")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/not-authorized")
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
