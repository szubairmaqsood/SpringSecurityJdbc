package com.example.springsecurityjdbc.SecurityConfigurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfigurations:WebSecurityConfigurerAdapter {

    var dataSource: DataSource;

    @Autowired
    constructor(_dataSource: DataSource)
    {
        this.dataSource = _dataSource
    }
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.jdbcAuthentication()
                ?.dataSource(this.dataSource);
    }

    /*
     This method is related to Authorization
     */
    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.antMatchers("/admin")?.hasRole("ADMIN")
                ?.antMatchers("/user")?.hasAnyRole("ADMIN","USER")
                ?.antMatchers("/")?.permitAll()
                ?.and()
                ?.formLogin()
    }

    /*
     Password Encoder
     */
    @Bean
    fun getPasswordEncoder():PasswordEncoder{
        return NoOpPasswordEncoder.getInstance();
    }
}