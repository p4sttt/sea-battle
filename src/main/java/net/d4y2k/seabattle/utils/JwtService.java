package net.d4y2k.seabattle.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.d4y2k.seabattle.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;

    public JwtService(@Value("${jwt.secretKey}") String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.jwtVerifier = JWT.require(algorithm).build();
    }

    public String createToken(User applicationUser) {
        return JWT.create()
                .withSubject(applicationUser.getId().toString())
                .withClaim("email", applicationUser.getEmail())
                .withClaim("role", applicationUser.getRole().getAuthority())
                .withIssuedAt(
                        new Date(System.currentTimeMillis())
                )
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
                )
                .sign(algorithm);
    }

    private Optional<DecodedJWT> decodedJWT(String token) {
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return Optional.of(decodedJWT);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public Optional<DecodedJWT> decodeToken(String token) {
        return decodedJWT(token);
    }

    public Optional<String> getEmailFromToken(String token) {
        Optional<DecodedJWT> decodedJWT = decodedJWT(token);
        return decodedJWT.map(jwt -> jwt.getClaim("email").asString());
    }

}
