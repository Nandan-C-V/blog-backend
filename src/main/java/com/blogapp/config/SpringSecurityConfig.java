package com.blogapp.config;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableWebMvc


public class SpringSecurityConfig {
	public static final String[] PUBLIC_URLS= {"/api/auth/**","/v3/api-docs","/v2/api-docs","/swagger-resources/**","/swagger-ui/**","/webjars/**"};

	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(PUBLIC_URLS).permitAll()
                    .requestMatchers(HttpMethod.GET).permitAll();
                    authorize.anyRequest().authenticated();
                });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public FilterRegistrationBean coresFilter() {
    	UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    	CorsConfiguration configuration=new CorsConfiguration();
    	configuration.setAllowCredentials(true);
    	configuration.addAllowedOriginPattern("*");
    	configuration.addAllowedHeader("Authorization");
    	configuration.addAllowedHeader("Content-type");
    	configuration.addAllowedHeader("Accept");
    	configuration.addAllowedMethod("POST");
    	configuration.addAllowedMethod("GET");
    	configuration.addAllowedMethod("DELETE");
    	configuration.addAllowedMethod("PUT");
    	configuration.addAllowedMethod("OPTIONS");
    	configuration.setMaxAge(3600L);
    	source.registerCorsConfiguration("/**", configuration);
    	
    	
    	FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(source));
    	bean.setOrder(-110);
    	return bean;
    }
}
