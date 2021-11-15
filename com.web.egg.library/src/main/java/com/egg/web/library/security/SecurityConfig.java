package com.egg.web.library.security;


import com.egg.web.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/signup", "/registro", "/css/*", "/assets/*", "/img/*").permitAll()
                    .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/inicio", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .disable();
    }
}
