package tn.cita.app.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.cita.app.config.filter.JwtRequestFilter;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.UserRoleBasedAuthority;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtRequestFilter jwtRequestFilter;
	
	@Bean
	AuthenticationProvider authenticationProvider() throws Exception {
		final var daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
		return daoAuthenticationProvider;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		return http
				.cors(CorsConfigurer<HttpSecurity>::disable)
				.csrf(CsrfConfigurer<HttpSecurity>::disable)
				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers(AppConstants.WHITELIST_URLS).permitAll()
						.requestMatchers(HttpMethod.GET, AppConstants.WHITE_BLACKLISTED_URLS_GET).authenticated()
						.requestMatchers(HttpMethod.GET, AppConstants.WHITELIST_URLS_GET).permitAll()
						.requestMatchers("/api/v*/customers/**")
							.hasRole(UserRoleBasedAuthority.CUSTOMER.name())
						.requestMatchers("/api/v*/employees/workers/**")
							.hasAnyRole(UserRoleBasedAuthority.WORKER.name(),
									UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.requestMatchers("/api/v*/employees/managers/**")
							.hasAnyRole(UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.requestMatchers("/api/v*/employees/owners/**")
							.hasRole(UserRoleBasedAuthority.OWNER.name())
						.requestMatchers("/api/v*/employees/**")
							.hasAnyRole(UserRoleBasedAuthority.WORKER.name(),
									UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.requestMatchers("/api/v*/admins/**")
							.hasRole(UserRoleBasedAuthority.ADMIN.name())
						.anyRequest().authenticated())
				.authenticationProvider(this.authenticationProvider())
				.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_FORBIDDEN, 
							"Error: Forbidden request, unauthorized access point")))
				.headers(headers -> headers
						.frameOptions(FrameOptionsConfig::sameOrigin))
				.sessionManagement(sessionManagement -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}




