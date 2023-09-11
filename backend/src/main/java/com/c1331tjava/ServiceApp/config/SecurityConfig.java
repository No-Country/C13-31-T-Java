package com.c1331tjava.ServiceApp.config;

import com.c1331tjava.ServiceApp.security.filter.JwtAuthorizationFilter;
import com.c1331tjava.ServiceApp.security.filter.JwtAuthenticationFilter;
import com.c1331tjava.ServiceApp.security.filter.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

/**
 * Configuración de seguridad para la aplicación.
 * Esta clase se anota con @Configuration, lo que indica que contiene métodos de configuración
 * que se utilizarán para construir el contexto de la aplicación.
 * También se anota con @EnableWebSecurity, lo que activa la funcionalidad de seguridad web
 * y permite personalizar la configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Utilidad para operaciones relacionadas con JWT (JSON Web Token).
     * Esta instancia se inyectará automáticamente utilizando la anotación @Autowired.
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Filtro de autorización basado en JWT.
     * Esta instancia se inyectará automáticamente utilizando la anotación @Autowired.
     */
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Detalles del servicio de usuario utilizado para la autenticación.
     * Esta instancia se inyectará automáticamente utilizando la anotación @Autowired.
     */
    @Autowired
    private UserDetailsService userDetailService;

    /**
     * Configuración del filtro de seguridad para las solicitudes HTTP.
     * Este método define el filtro de seguridad personalizado que utilizará JWT para autenticar
     * las solicitudes HTTP y autorizar los recursos protegidos.
     *
     * @param http                  Objeto HttpSecurity utilizado para configurar la seguridad HTTP.
     * @param authenticationManager Objeto AuthenticationManager utilizado para autenticar las solicitudes.
     * @return El filtro de seguridad configurado.
     * @throws Exception Si se produce algún error al configurar el filtro.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtil);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            .requestMatchers("/api/v1/client/**").hasRole("Client")
                            .requestMatchers("/api/v1/provider/**").hasRole("Provider")
                            .requestMatchers("/api/v1/users").authenticated()
                            .anyRequest().permitAll();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configuración del encriptador de contraseñas utilizado en la autenticación.
     *
     * @return El encriptador de contraseñas BCryptPasswordEncoder.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración del administrador de autenticación.
     * Este método configura el AuthenticationManager utilizado para autenticar las solicitudes.
     *
     * @param httpSecurity Objeto HttpSecurity utilizado para obtener el AuthenticationManagerBuilder compartido.
     * @return El AuthenticationManager configurado.
     * @throws Exception Si se produce algún error al configurar el AuthenticationManager.
     */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider(userDetailService, passwordEncoder())).build();
    }

    /**
     * Proveedor de autenticación DAO (Data Access Object).
     * Este método configura el DaoAuthenticationProvider utilizado para autenticar las solicitudes
     * utilizando un servicio de detalles de usuario personalizado y un encriptador de contraseñas.
     *
     * @param userDetailService El servicio UserDetailsService utilizado para obtener detalles de usuario.
     * @param passwordEncoder   El encriptador de contraseñas utilizado para verificar contraseñas.
     * @return El DaoAuthenticationProvider configurado.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    public String getUserNameFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
