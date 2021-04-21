package com.bednaruk.Track_WebService;


import com.bednaruk.Track_WebService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private com.bednaruk.Track_WebService.UserDetailsServiceImpl userDetailsService;
    private UserRepo userRepo;

    @Autowired
    public WebSecurityConfig(com.bednaruk.Track_WebService.UserDetailsServiceImpl userDetailsService, UserRepo UserRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo = UserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


@Override
protected void configure(HttpSecurity http) throws Exception {
        try{
            http.authorizeRequests()
                    .antMatchers("/registry").permitAll()
                    .antMatchers("/trackadd").authenticated()
                    .and()
                    .logout()
                    .and()
                    .formLogin().permitAll()
                    .and()
                    .csrf().disable();

        }
        catch (Exception e){
            e.getMessage();
        }
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
