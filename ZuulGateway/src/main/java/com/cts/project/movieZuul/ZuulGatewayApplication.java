package com.cts.project.movieZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableDiscoveryClient // It acts as a eureka client
@EnableZuulProxy      // Enable Zuul gateway
@SpringBootApplication
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() 
//	{
//	  UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//	   .build();
//	  return new InMemoryUserDetailsManager(user);
//	 }

}
