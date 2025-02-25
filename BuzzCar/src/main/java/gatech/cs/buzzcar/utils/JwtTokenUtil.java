package gatech.cs.buzzcar.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "HelloWorld@666";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private static final String ROLE_KEY = "role";
    private static final String USER_NAME_KEY = "username";

    public static String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_KEY, role);
        map.put(USER_NAME_KEY, username);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setClaims(map)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String getUserRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get(ROLE_KEY).toString();
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get(USER_NAME_KEY).toString();
    }

    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // Invalid signature
        } catch (MalformedJwtException ex) {
            // Invalid JWT token
        } catch (ExpiredJwtException ex) {
            // Expired JWT token
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty
        }
        return false;
    }
}
