package co.offcampusjobs.util.jwt;

import co.offcampusjobs.model.User;
import co.offcampusjobs.util.constant.SecurityConstant;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static co.offcampusjobs.util.constant.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTTokenProvider {
    @Value("${jwt.secret}")
    private String secret;

    public String generateJWTToken(User user) {
        String[] claims = getClaimsFromUser(user);
        return JWT.create().withIssuer(OFF_CAMPUS_JOBS_LLC).withAudience(OFF_CAMPUS_JOBS_CREATOR)
                .withIssuedAt(new Date()).withSubject(user.getEmail()).withArrayClaim(SecurityConstant.AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(secret.getBytes()));
    }

    private String[] getClaimsFromUser(User user) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : user.getA)
    }
}
