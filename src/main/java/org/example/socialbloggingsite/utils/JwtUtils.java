package org.example.socialbloggingsite.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.users.repositories.RefreshTokenRepository;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.example.socialbloggingsite.exceptions.customs.ErrorCode.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JwtUtils {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    @Value("${security.jwt.secret-key}")
    String secretKey;

    //Time expiration token
    @Value("${security.jwt.expiration-time}")
    long expirationTime;

    @Value("${security.jwt.expiration-refresh-time}")
    long expirationRefreshTime;


    //Get User Name From JWT Token
    public String extractUser(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUser(token);
        if (!refreshTokenRepository.existsByUser(userRepository.findByUsername(username).get())) {
            throw new CustomRunTimeException(INVALID_TOKEN);
        }

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Export data from JWT
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Export data in token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    //Giai ma key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims,userDetails,expirationTime);
    }

    private String buildToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            long expiration
    ){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public void TokenInvalid(String token) {
        Claims claims = extractAllClaims(token);
        log.info(claims.toString());
        claims.remove("exp");

//        claims.setExpiration(new Date());
        log.info(claims.getExpiration().toString());
        log.info("hi token");
        log.info(claims.getIssuedAt().toString());
    }

    //create refreshToken
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }
    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildRefreshToken(claims,userDetails,expirationRefreshTime);
    }

    private String buildRefreshToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            long expiration
    ){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

}
