package org.vivek.placementportal.security;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.web.cors.CorsConfiguration;
import org.vivek.placementportal.models.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors().configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                    corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                    corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
                    return corsConfiguration;
                }).and()
                .authorizeHttpRequests(
                        req -> req.requestMatchers(
                                        "/api/v1/auth/**",
                                "/swagger-ui/**"
                                )
                                .permitAll()
                                .requestMatchers("/api/v1/student/**").hasAnyAuthority(Role.STUDENT_ROLE.name())
                                .requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ADMIN_ROLE.name())
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/logout").addLogoutHandler((
                                (request, response, authentication) -> SecurityContextHolder.clearContext())
                        )
                );

        return http.build();
    }

}
