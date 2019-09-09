package pet.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pet.jwt.JWTAuthenFilter;
import pet.jwt.JWTAuthenticationFilter;
import pet.jwt.JWTLoginFilter;
import pet.jwt.SkipPathRequestMatcher;
import pet.service.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//                   .withUser("mkyong").password("123456").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//	}

	 @Autowired
	 private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(jwtAuthenticationProvider);
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery( 
			"select username,role from users a inner join user_role b on (a.id = b.userid) inner join roles  c on (b.roleid = c.role_id ) where a.username=?")
		//"select username,role from bullshit where username=?");
		;
		 // Create a default account
//	    auth.inMemoryAuthentication()
//	        .withUser("admin")
//	        .password("password")
//	        .roles("ADMIN");
	}
	
//	 @Override
//     public void configure(WebSecurity web) throws Exception {
//       web.ignoring()
//         .antMatchers(HttpMethod.OPTIONS);
//     }
	
	//.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//	    http.cors().disable().authorizeRequests()
//		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.and()
//		    .formLogin().loginPage("/login").failureUrl("/login?error")
//		    .usernameParameter("username").passwordParameter("password")
//		.and()
//		    .logout().logoutSuccessUrl("/login?logout")
//		.and()
//		    .csrf().disable();
		
//		 List<String> pathsToSkip = new ArrayList<String>();
//		 pathsToSkip.add("/login");
//	        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, "/**");
//		 http.cors().and().csrf().disable().authorizeRequests()
//		   .antMatchers("/dish").access("hasRole('ADMIN')")
//	        .antMatchers("/").permitAll()
//	        .antMatchers(HttpMethod.POST, "/login").permitAll()
//	        .anyRequest().authenticated()
//	        .and()
//	        // We filter the api/login requests
//	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//	                UsernamePasswordAuthenticationFilter.class)
//	        // And filter other requests to check the presence of JWT in header
//	        .addFilterBefore(new JWTAuthenticationFilter(),
//	                UsernamePasswordAuthenticationFilter.class)
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		
		
//		 List<String> pathsToSkip = new ArrayList<String>();
//		 pathsToSkip.add("/login");
//	        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, "/**");
		 http.cors().and().csrf().disable().authorizeRequests()
			//   .antMatchers("/dish").access("hasRole('ADMIN')")
		       .antMatchers("/getTour").permitAll()
	 	       .antMatchers("/getTours").permitAll()
	 	     //.antMatchers("/createtour").permitAll()
		    //    .antMatchers(HttpMethod.POST, "/login").permitAll()
		     //  .antMatchers("/createtour").authenticated()
		    .antMatchers("/createtour").access("hasRole('ADMIN')")
		        //.antMatchers("/fuckJava").permitAll()
		        .and()
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class)
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}