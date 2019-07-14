package com.eksad.latihanrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eksad.latihanrest.service.UsersService;

@Configuration
@EnableWebSecurity// menjadikan class sebagai konfigurasi default keamanan saat mengakses web
public class SecurityConfig extends WebSecurityConfigurerAdapter { // berisi method yang akan diimplement sebagai konfigurasi
	
	@Autowired
	private UsersService usersService;//class untuk mencari dan menampung data username, password, role
	
 	@Override
 	protected void configure(HttpSecurity http) throws Exception {//konfigurasi akses melalui controller
 		http.httpBasic().and(). //membaca header basic auth
 		csrf().disable().// mengakses seluruh method
 		authorizeRequests(). //akses masuk ke url
 		antMatchers("/admin/**").hasAuthority("ADMIN"). //url yang mau diakses, hak akses
 		antMatchers("/user/**").hasAnyAuthority("ADMIN","USER").
 		and().formLogin().permitAll();//menampilkan login form bentuk html
 				
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//konfigurasi mencari akses username password dan role
 		
 		auth.userDetailsService(usersService).passwordEncoder(encoder());// memeriksa kredensial (username, password, role) apakah tersedia atau tidak
 		
 	/*	String admin= "admin";
 		String adminPassword= encoder().encode("1234");
 	
 		String user= "user";
 		String userPassword= encoder().encode("qwerty");
 	
 		auth.inMemoryAuthentication()
 			.withUser(admin).password(adminPassword).roles(roles)("ADMIN","USER");
 		
 		auth.inMemoryAuthentication()
			.withUser(user).password(userPassword).roles("USER");
 		*/
 	}
 	
 	@Bean
 	public BCryptPasswordEncoder encoder() {// encode / enkrip password
 		return new BCryptPasswordEncoder();
 	}
}
