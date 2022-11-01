package tn.cita.app.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import tn.cita.app.config.filter.JwtRequestFilter;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.UserRoleBasedAuthority;

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
		return http.cors(cors -> cors.disable())
				.csrf(csrf -> csrf.disable())
				.authorizeRequests(authorizeRequests -> authorizeRequests
						.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.antMatchers(AppConstants.WHITELIST_URLS).permitAll()
						.mvcMatchers(HttpMethod.GET, AppConstants.WHITE_BLACKLISTED_URLS_GET).authenticated()
						.antMatchers(HttpMethod.GET, AppConstants.WHITELIST_URLS_GET).permitAll()
						.mvcMatchers("/api/v*/customers/**")
							.hasRole(UserRoleBasedAuthority.CUSTOMER.name())
						.mvcMatchers("/api/v*/employees/workers/**")
							.hasAnyRole(UserRoleBasedAuthority.WORKER.name(),
									UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.mvcMatchers("/api/v*/employees/managers/**")
							.hasAnyRole(UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.mvcMatchers("/api/v*/employees/owners/**")
							.hasRole(UserRoleBasedAuthority.OWNER.name())
						.mvcMatchers("/api/v*/employees/**")
							.hasAnyRole(UserRoleBasedAuthority.WORKER.name(),
									UserRoleBasedAuthority.MANAGER.name(),
									UserRoleBasedAuthority.OWNER.name())
						.mvcMatchers("/api/v*/admins/**")
							.hasRole(UserRoleBasedAuthority.ADMIN.name())
						.anyRequest().authenticated())
				.authenticationProvider(this.authenticationProvider())
				.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_FORBIDDEN, 
							"Error: Forbidden request, unauthorized access point")))
				.headers(headers -> headers
						.frameOptions()
						.sameOrigin())
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














