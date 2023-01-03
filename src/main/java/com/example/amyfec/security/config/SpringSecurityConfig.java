package com.example.amyfec.security.config;

import com.example.amyfec.security.filter.JwtAuthenticationFilter;
import com.example.amyfec.security.filter.JwtAutorisationFilter;
import com.example.amyfec.security.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserService userService;
    @Autowired private JwtAutorisationFilter jwtAutorisationFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()

                .antMatchers("/api/v1/article/**").permitAll()
                .antMatchers("/api/v1/report/**").permitAll()
                .antMatchers("/api/v1/admin/**").permitAll()
                .antMatchers("/api/v1/user/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/login/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/login/**").hasAuthority("ROLE_CHEF")
                .anyRequest().authenticated();
        //http.authorizeRequests().anyRequest().authenticated();
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(jwtAutorisationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
