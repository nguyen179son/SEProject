package Helper;

import Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JWTHandler {

    private static final String SECRET = "HelloWorld!";
    private static final long DAY = 86400000;   //mili-second


    public static String generateToken(String id, long length) {
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (length * DAY)); //day
        try {
            String token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setId(id)
                    .setExpiration(exp)
                    .claim("CreatedDate", new Date().toString())
                    .signWith(SignatureAlgorithm.HS256, SECRET.getBytes("UTF-8"))
                    .compact();
            return token;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static int verifyToken(String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();

            if(User.exist(Integer.parseInt(claims.getId())))
                return Integer.parseInt(claims.getId());
            else return -1;
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return -3;
        }
        catch (SignatureException e){
            e.printStackTrace();
            return -2;
        }
        catch (Exception e){
            e.printStackTrace();
            return -4;
        }
    }
}
