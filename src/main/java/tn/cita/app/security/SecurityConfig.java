package tn.cita.app.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import tn.cita.app.config.filter.JwtRequestFilter;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.UserRoleBasedAuthority;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService)
			.passwordEncoder(this.passwordEncoder);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.cors().disable()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(AppConstant.WHITELIST_URLS).permitAll()
				.antMatchers(HttpMethod.GET, AppConstant.WHITE_BLACKLISTED_URLS_GET).authenticated()
				.antMatchers(HttpMethod.GET, AppConstant.WHITELIST_URLS_GET).permitAll()
				.antMatchers("/api/v*/customers/**")
					.hasRole(UserRoleBasedAuthority.CUSTOMER.name())
				.antMatchers("/api/v*/workers/**")
					.hasAnyRole(UserRoleBasedAuthority.WORKER.name(),
							UserRoleBasedAuthority.MANAGER.name(),
							UserRoleBasedAuthority.OWNER.name())
				.antMatchers("/api/v*/managers/**")
					.hasAnyRole(UserRoleBasedAuthority.MANAGER.name(),
							UserRoleBasedAuthority.OWNER.name())
				.antMatchers("/api/v*/owners/**")
					.hasRole(UserRoleBasedAuthority.OWNER.name())
				.anyRequest().authenticated()
			.and()
			.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> 
				response.sendError(HttpServletResponse.SC_FORBIDDEN, 
						"Error: Forbidden request, unauthorized access point")))
			.headers()
				.frameOptions()
				.sameOrigin()
			.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
}














