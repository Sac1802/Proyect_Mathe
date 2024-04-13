package proyect_U.Security.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyect_U.Model.Register;

@Service
public class JsonWebToken {
    String apiSecret = "DemoMath";
    public JsonWebToken(){
    }

    public String generate(Register register){
        String response;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            response = com.auth0.jwt.JWT.create()
                    .withIssuer("Register")
                    .withSubject(register.getUsername())
                    .withClaim("id", register.getId())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            response = "Invalid Token";
        }
        return response;
    }

    /*public ResponseEntity<String> validateJWT(String token){
        DecodedJWT decodedJWT;
        ResponseEntity response;
        if (token == null){
            throw new RuntimeException();
        }else {
            try {
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("Register")
                        .build();
                decodedJWT = verifier.verify(token);
                response = ResponseEntity.ok().body(decodedJWT);
            }catch (JWTVerificationException exception){
                response = ResponseEntity.unprocessableEntity().body("Invalid Token");
            }
        }
        return response;
    }*/
}
