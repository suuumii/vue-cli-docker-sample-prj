package net.asdevs.myhomegc2.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        // AuthenticationManagerの設定
        this.authenticationManager = authenticationManager;

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        setUsernameParameter("username");
        setPasswordParameter("password");

        // ログイン成功時
        this.setAuthenticationSuccessHandler((req, res, ex) -> {
            // トークンの作成
            String token = JWT.create()
                .withIssuer("net.asdevs.myhomegc2")
                .withClaim("username", ex.getName())
                .sign(Algorithm.HMAC256("secret"));
            res.setHeader("X-AUTH-TOKEN", token);
            res.setStatus(200);
        });

        // ログイン失敗時
        this.setAuthenticationFailureHandler((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserForm form = new ObjectMapper().readValue(request.getInputStream(), UserForm.class);
            return this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
