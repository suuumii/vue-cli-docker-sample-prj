package net.asdevs.myhomegc2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().ignoringAntMatchers("/sample");
        // 認証
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/crypt").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/crypt").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/api/**").permitAll();
                // .antMatchers("/api/**").authenticated();
        // 独自フィルターの利用
        // デフォルトのAuthenticationManagerを利用する
        http.addFilter(new AuthenticationFilter(authenticationManager()));
        // csrfを無効にしておく
        // またCookieを利用してcsrf対策を行う
        http.csrf().ignoringAntMatchers("/api/**");
    }
}
