package ru.kinoguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO Enable CSRF protection

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/user/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/films/**", "/js/**", "/css/**", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf() // disabling because it causes errors when handling some forms
                .disable()
                // https://stackoverflow.com/questions/21128058/invalid-csrf-token-null-was-found-on-the-request-parameter-csrf-or-header
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // .logoutUrl("/logout") did not work and I had to to it like this WHY?
                .logoutSuccessUrl("/users/login?logout")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        // TODO Add encryption after we start up our KinoGuide (C) in production
//                .passwordEncoder(bCryptPasswordEncoder());
    }

}
