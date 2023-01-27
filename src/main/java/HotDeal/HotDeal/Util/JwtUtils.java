package HotDeal.HotDeal.Util;

import HotDeal.HotDeal.Domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class JwtUtils {

    public static String getJwtTokenFromAuthorizationHeader(String header) {
        if (header == null || header.length() < 10 || !header.substring(0, 7).equals("Bearer ")) {
            return null;
        }
        return header.substring(7).trim();
    }

    public static String generateJwtToken(User user) {
        Date currentTimestamp = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("zigdeal")
                .setIssuedAt(currentTimestamp)
                .setExpiration(DateUtils.addDays(currentTimestamp, 7)) // 유효기간: 7일
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, "garden")
                .compact();
    }

    private static Claims parseJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey("garden")
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateJwtToken(String token) {
        if (token == null || token.isEmpty() || !token.contains(".")) {
            return false;
        }
        try {
            Claims claims = parseJwtToken(token);
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUserIdByJwtToken(String token) {
        Claims claims = parseJwtToken(token);
        return claims.get("id", String.class);
    }

}