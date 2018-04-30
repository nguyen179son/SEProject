package Helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JWTHandler {

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    private static final long DAY = 86400000;   //mili-second


    public static String generateToken(String id, long length) {
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (length * DAY)); //day

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setId(id)
                .setExpiration(exp)
                .claim("CreatedDate", new Date().toString())
                .claim("Expired Date", exp.toString())
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();

        return token;
    }

    public static void verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(base64SecretBytes)
                    .parseClaimsJws(token).getBody();
            System.out.println("----------------------------");
            System.out.println("ID: " + claims.getId());
            System.out.println("CreatedDate: " + claims.get("CreatedDate"));
            System.out.println("ExpiratedDate: " + claims.get("Expired Date"));
            System.out.println("Expiration: " + claims.getExpiration());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
