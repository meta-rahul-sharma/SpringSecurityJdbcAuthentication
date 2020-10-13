package com.security.springsecuritywithdatabaseconnectivity.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


		@Autowired
		DataSource dataSource;

//		/**
//		 * @author rahul sharma
//		 *
//		 * @param registry
//		 */
//		public void addViewControllers(ViewControllerRegistry registry) {
//				registry.addViewController("/home").setViewName("home");
//				registry.addViewController("/").setViewName("home");
//				registry.addViewController("/hello").setViewName("hello");
//				registry.addViewController("/login").setViewName("login");
//		}

		/**
		 * @author rahul sharma
		 * method defines which URL paths should be secured and which should not
		 *
		 * @param http
		 * @throws Exception
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
				http
				.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/hello")
				.permitAll()
				.and()
				.logout()
				.permitAll();

				http.csrf().disable();
		}

		/**
		 * @autho rahul sharma
		 * To set up in memory authentication of user with password encoder
		 * @param auth
		 * @throws Exception
		 */
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
				System.out.println(new BCryptPasswordEncoder().encode("password"));
//				auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).dataSource(dataSource);

				auth.jdbcAuthentication()
								.dataSource(dataSource)
								.passwordEncoder(NoOpPasswordEncoder.getInstance())
								.usersByUsernameQuery(
								"SELECT username, password, enabled from users where username = ?")
								.authoritiesByUsernameQuery(
												"SELECT username, role as authority from users where username = ?"
								);
		}

//		@Bean
//		public PasswordEncoder passwordEncoder() {
//				return new BCryptPasswordEncoder();
//		}
}