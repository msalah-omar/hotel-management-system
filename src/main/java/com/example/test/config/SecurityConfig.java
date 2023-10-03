package com.example.test.config;

import com.example.test.security.RestAuthenticationEntryPoint;
import com.example.test.security.SecurityUserService;
import com.example.test.security.TokenAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  SecurityUserService securityUserService;

   private  TokenAuthenticationFilter tokenAuthenticationFilter;

    private  RestAuthenticationEntryPoint restAuthenticationEntryPoint;



    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/auth/**").permitAll();
                //USER
//                .antMatchers( "/booking/**").hasAnyRole("USER");
//
//                //MANGER
//                .antMatchers(HttpMethod.GET,"/checkout-booking/{id}" , "/{hotelId}/current-safe-balance").hasAnyRole("MANAGER")
//                .antMatchers(HttpMethod.PUT,"/booking/{id}").hasAnyRole("MANAGER")
//                .antMatchers(HttpMethod.POST, "/invoice").hasAnyRole("MANAGER")
//                //ADMIN
//                .antMatchers(HttpMethod.GET,"/booking/views-booking", "/booking/confirm-booking/{id}").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/booking/{id}").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/booking").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/booking/{id}").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/v3/api-docs/*", "/v3/api-docs", "/swagger-ui.html", "/swagger-ui/*").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
