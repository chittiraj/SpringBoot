package com.dl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	@Bean
	static PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// when we write 25, 26 line it's going to unable the csrf token.
		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> {
			auth.anyRequest().authenticated();
		})
				// It's going to enable the all http basic authorize with defult settings.
				.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}

	// user details service or method
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		UserDetails userDetails_user = User.builder().username("user").password(passwordEncoder().encode("user"))
				.roles("USER").build();

		UserDetails userDetails_admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(userDetails_user, userDetails_admin);
	}

}
