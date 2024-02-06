package com.myblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //THIS IS IN-MEMORY AUTHENTICATION FOR TESTING PURPOSE ONLY WHEN INTERACTION TAKES PLACE BETWEEN API AND BACKEND
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http)throws Exception{

        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/post").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
              //  .antMatchers(HttpMethod.DELETE,"/api/post").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    //PRE-AUTHORIZATION CAN ALSO DONE IN ANOTHE WAY-->.antMatchers(HttpMethod.POST,"/api/post").hasAnyRole("ADMIN")
//HERE WE ARE NOT IUSING ANNOTRATION PRE AUTHORIZE AND DIRECTLY DEFINING THE ROLE OF AUTHORIZATION
    }
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 =
                User.builder().username("srk").password(passwordEncoder()
                        .encode("srk123")).roles("USER").build();
        UserDetails user2 =
                User.builder().username("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1,user2);
    }



}
