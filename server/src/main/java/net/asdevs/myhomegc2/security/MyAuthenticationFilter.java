package net.asdevs.myhomegc2.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    // トークン有効期限（ms）
    public static final Long EXPIRATION_TIME = 1000 * 60 * 60L;

    public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
        // AuthenticationManagerの設定
        this.authenticationManager = authenticationManager;

        // ログインパスを設定
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        // ログイン用パラメータの設定
        setUsernameParameter("username");
        setPasswordParameter("password");

        // ログイン成功時
        this.setAuthenticationSuccessHandler((req, res, ex) -> {
            // トークンの作成
            String token = JWT.create()
                    .withIssuer("net.asdevs.myhomegc2") // 発行者
                    .withClaim("username", ex.getName())
                    .sign(Algorithm.HMAC256("secrethogehoge")); // アルゴリズム指定
            res.setHeader("X-AUTH-TOKEN", "Bearer " + token); // tokeをX-AUTH-TOKENにセット
            res.setStatus(200);
        });

        // ログイン失敗時
        this.setAuthenticationFailureHandler((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            ServletInputStream stream = request.getInputStream();
            AuthenticationRequestValue form = new ObjectMapper().readValue(stream, AuthenticationRequestValue.class);
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
