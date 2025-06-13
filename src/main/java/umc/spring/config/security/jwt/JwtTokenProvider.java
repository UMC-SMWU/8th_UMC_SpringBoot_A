package umc.spring.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.properties.Constants;
import umc.spring.config.properties.JwtProperties;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public String generateToken(Authentication authentication, TokenType tokenType) {
        String email = authentication.getName();
        if (tokenType == null){
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }

        Date accessTokenExpiredTime;
        switch (tokenType) {
            case ACCESS -> {
                accessTokenExpiredTime = new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess());
            }
            case REFRESH -> {
                accessTokenExpiredTime = new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()*24);
            }
            default -> {
                throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
            }
        }

        return Jwts.builder()
                .setSubject(email)
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                .claim("token_type", tokenType)
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiredTime)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, TokenType tokenType) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return hasProperType(token, tokenType);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims parseClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (SignatureException e) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
    }


    public Authentication getAuthentication(String token){
        Claims claims = parseClaims(token);

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        User principal = new User(email, "", Collections.singleton(() -> role));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    public static String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)){
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }

    public Authentication extractAuthentication(HttpServletRequest request){
        String accessToken = resolveToken(request);
        if (accessToken == null || !validateToken(accessToken, TokenType.ACCESS)){
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
        return getAuthentication(accessToken);
    }

    private boolean hasProperType(String token, TokenType tokenType){
        Claims claims = parseClaims(token);
        String tokenTypeClaim = (String) claims.get("token_type");

        return tokenType == TokenType.valueOf(tokenTypeClaim);
    }

}
