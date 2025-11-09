package com.example.fitTrackBackend.Service;

import com.example.fitTrackBackend.Repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

@Service
public class JwtService {
    private final UserRepo userRepo;
    @Value("${security.jwt.secret-key}")
     private String secretKey;

     //expiration of token
     @Value("${security.jwt.expiration-time}")
     private long jwtExpiration;

    public JwtService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String extractUsername(String token){
         return extractClaim(token, Claims::getSubject);
     }
     public <T> T extractClaim(String Token, Function<Claims, T>claimsResolver){
        final Claims claims = extractAllClaims(Token);
        return claimsResolver.apply(claims);
     }

     public String generateToken(UserDetails userDetails){
         return generateToken(new HashMap<>(), userDetails);
     }

     public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
         return buildToken(extraClaims, userDetails, jwtExpiration);
     }

     public long getExpirationTime(){
         return jwtExpiration;
     }

     private String buildToken(
         Map<String, Object> extraClaims,
         UserDetails userDetails,
         long expiration

     ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
     }
     public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
     }

     private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
     }

     private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
     }

     private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

     }

     private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
     }
}
