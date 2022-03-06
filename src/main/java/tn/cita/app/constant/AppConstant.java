package tn.cita.app.constant;

import java.time.Duration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Define all constants related to the App
 * @author selim
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstant {
	
	/**
	 * DateTime custom API formats
	 */
	public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
	public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
	public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
	
	/**
	 * API Context to setup Version for webservices
	 */
	public static final String API_CONTEXT_V0 = "/api/v0";
	
	/**
	 * WhiteList URLs to be authorised with http security
	 */
	public static final String[] WHITELIST_URLS = {
			"/", 
			"/h2-console/**", 
			"/api/v**/authentication/**",
			"/api/v**/registration/**",
	};
	
	/**
	 * page size for Pageable objects
	 */
	public static final int PAGE_SIZE = 50;
	
	public static final String MAIL_SOURCE = "cita.team.mail@gmail.com";
	
	/**
	 * Verification Token expires after specified minutes from localdatetime.now
	 */
	public static final long EXPIRES_AT_FROM_NOW = Duration.ofMinutes(30).toMinutes();
	
}










