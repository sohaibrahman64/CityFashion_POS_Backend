package com.cityfashionpos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing/API calls
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/api/auth/login").permitAll() // Allow login endpoint
                        .antMatchers("/api/menu/*").permitAll()
                        .antMatchers("/api/categories/*").permitAll()
                        .antMatchers("/api/barcode/*").permitAll()
                        .antMatchers("/api/products/*").permitAll()
                        .antMatchers("/api/products/**").permitAll()
                        // .antMatchers("/api/products/updateProductNew/**").permitAll()
                        .antMatchers("/api/sales/*").permitAll()
                        .antMatchers("/api/sales/*/*").permitAll()
                        .antMatchers("/api/customers/*").permitAll()
                        .antMatchers("/api/customers/**").permitAll()
                        .antMatchers("/api/payment-modes/*").permitAll()
                        .antMatchers("/api/invoice/*").permitAll()
                        .antMatchers("/api/invoice/**").permitAll()
                        .antMatchers("/api/suppliers/**").permitAll()
                        .antMatchers("/api/inventory/**").permitAll()
                        .antMatchers("/api/inventory/**/**").permitAll()
                        .antMatchers("/api/purchases/**").permitAll()
                        .antMatchers("/api/barcode/**").permitAll()
                        .antMatchers("/api/stock-adjustments/**").permitAll()
                        .antMatchers("/api/product-transactions/*").permitAll()
                        .antMatchers("/api/product-transactions/**").permitAll()
                        .antMatchers("/api/new-sales-invoice/*").permitAll()
                        .antMatchers("/api/new-sales-invoice/**").permitAll()
                        .antMatchers("/api/tax-rates/*").permitAll()
                        .antMatchers("/api/tax-rates/**").permitAll()
                        .antMatchers("/api/sales-transactions/*").permitAll()
                        .antMatchers("/api/sales-transactions/**").permitAll()
                        .anyRequest().authenticated() // Protect other endpoints
                )
                .httpBasic(Customizer.withDefaults()); // Optional: enable basic auth

        return http.build();
    }

}
