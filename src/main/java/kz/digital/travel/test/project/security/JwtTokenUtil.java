package kz.digital.travel.test.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtTokenUtil {

    private final String SECRET_KEY = "yourSecretKey";  // Это секретный ключ для подписания токенов

    // Метод для валидации токена
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;  // Если токен можно распарсить, значит он валиден
        } catch (SignatureException e) {
            return false;  // Токен невалиден
        }
    }

    // Метод для извлечения имени пользователя из токена
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}