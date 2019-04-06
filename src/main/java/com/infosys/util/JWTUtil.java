package com.infosys.util;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

import com.infosys.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

public class JWTUtil {
	public class JwtUtil {

	    @Value("${jwt.secret}")
	    private String secret;
	    
	   // @SuppressWarnings(value = "SE_BAD_FIELD", justification = "It's okay here")
	    private Clock clock = DefaultClock.INSTANCE;

	    @Value("${jwt.expiration}")
	    private Long expiration;

	    /**
	     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
	     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
	     * 
	     * @param token the JWT token to parse
	     * @return the User object extracted from specified token or null if a token is invalid.
	     */
	    public User parseToken(String token) {
	        try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();

	            User u = new User();
	            u.setName(body.getSubject());
	            u.setId(Integer.parseInt((String) body.get("userId")));
	            //u.setRole((String) body.get("role"));

	            return u;

	        } catch (JwtException | ClassCastException e) {
	            return null;
	        }
	    }

	    /**
	     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
	     * User object. Tokens validity is infinite.
	     * 
	     * @param u the user for which the token will be generated
	     * @return the JWT token
	     */
	    public String generateToken(User u) {
	        Claims claims = Jwts.claims().setSubject(u.getName());
	        claims.put("userId", u.getId() + "");
	        claims.put("role", u.getRole());

	        return Jwts.builder()
	                .setClaims(claims)
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	    }
	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(clock.now());
	    }
	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }

	}
}
