package cz.polankam.jaclp.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().and()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .cors().and()
                .headers().frameOptions().sameOrigin().and();

        // Allow swagger
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**")
                .permitAll();
        // Restrict other request only for authenticated users
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("manager").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
