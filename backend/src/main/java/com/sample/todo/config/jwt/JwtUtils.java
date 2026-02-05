package com.sample.todo.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "bezKoderSecretKeybezKoderSecretKeybezKoderSecretKey";

    // 有効期限（24時間）
    private int jwtExpirationMs = 86400000;

    // トークンを生成するメソッド
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) // ユーザー名をセット
                .setIssuedAt(new Date()) // 発行日
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // 有効期限
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256) // 署名
                .compact();
    }

    // トークンからユーザー名を取り出すメソッド
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // トークンが有効か検証するメソッド
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // 本来は期限切れ、改ざんなどの例外をキャッチしてログを出す
        }
        return false;
    }
}
