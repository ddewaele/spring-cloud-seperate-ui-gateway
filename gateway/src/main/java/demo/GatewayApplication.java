package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@SpringBootApplication
@EnableOAuth2Sso
@EnableZuulProxy
public class GatewayApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Configuration
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
////			http
////					.httpBasic()
////					.and()
////					.logout()
////					.and()
////					.authorizeRequests()
////					.antMatchers("/index.html", "/login", "/").permitAll()
////					.anyRequest().authenticated()
////					.and()
////					.csrf().csrfTokenRepository(csrfTokenRepository())
////					.and()
////					.addFilterAfter(csrfHeaderFilter(), SessionManagementFilter.class);
////
//		http.authorizeRequests()
//				.antMatchers("/index.html", "/home.html", "/js/**","/").permitAll().anyRequest()
//				.authenticated().and()
//				.csrf().csrfTokenRepository(csrfTokenRepository());
//
//		}
//
//		private Filter csrfHeaderFilter() {
//			return new OncePerRequestFilter() {
//				@Override
//				protected void doFilterInternal(HttpServletRequest request,
//												HttpServletResponse response, FilterChain filterChain)
//						throws ServletException, IOException {
//					CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//							.getName());
//					if (csrf != null) {
//						Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//						String token = csrf.getToken();
//						if (cookie == null || token != null
//								&& !token.equals(cookie.getValue())) {
//							cookie = new Cookie("XSRF-TOKEN", token);
//							cookie.setPath("/");
//							response.addCookie(cookie);
//						}
//					}
//					filterChain.doFilter(request, response);
//				}
//			};
//		}
//
//		private CsrfTokenRepository csrfTokenRepository() {
//			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//			repository.setHeaderName("X-XSRF-TOKEN");
//			return repository;
//		}
//	}



//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/js/**","/ui/js/**");
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {


//				http.antMatcher("/**").authorizeRequests()
//				.antMatchers("/ui/index.html", "/ui/home.html", "/ui/**").permitAll().anyRequest().authenticated();
//				.and().csrf().csrfTokenRepository(csrfTokenRepository())
//				.and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);


//		http.antMatcher("/**").authorizeRequests()
//				.antMatchers("/ui/index.html", "/ui/home.html", "/ui/**").permitAll().anyRequest()
//				.authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository())
//				.and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);

//		http.logout().and()
//				//.antMatcher("/**")
//				.authorizeRequests()
//				.antMatchers("/ui/index.html", "/ui/home.html", "/ui/**").permitAll()
//				.anyRequest().authenticated(); //.and()
//				.csrf().csrfTokenRepository(csrfTokenRepository()).ignoringAntMatchers("/uaa/‌​oauth/token").and()
//				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);


//		this kinda works
		http.logout()
				.and()
					.authorizeRequests()
				.antMatchers("/ui/index.html", "/ui/home.html", "ui/css/**", "/ui/js/**").permitAll().anyRequest().authenticated();
				//.antMatchers("/index.html", "/home.html", "/js/**","/css/**").permitAll().anyRequest().authenticated();

// The adding http.antMatcher("/**").authorizeRequests() is doesn't work.

//				.and().csrf().csrfTokenRepository(csrfTokenRepository())
//				.and().addFilterAfter(csrfHeaderFilter(), SessionManagementFilter.class);









//				.and().csrf().csrfTokenRepository(csrfTokenRepository()).ignoringAntMatchers("/uaa/**").and()
//				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);

//				.and().csrf().csrfTokenRepository(csrfTokenRepository())
//				.and().addFilterAfter(csrfHeaderFilter(), SessionManagementFilter.class);
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
							throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null
							|| token != null && !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/ui");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
