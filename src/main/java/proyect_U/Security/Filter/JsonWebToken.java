package proyect_U.Security.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;
import proyect_U.Model.Register;
import proyect_U.Repository.RegisterRepository;

@Service
public class JsonWebToken {
    String apiSecret = "DemoMath";
    private RegisterRepository registerRepository;
    public JsonWebToken(){
    }

    public String generate(Register register){
        String response;
        Register user = registerRepository.findByUsername(register.getUsername());
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            response = JWT.create()
                    .withIssuer("Register")
                    .withSubject(register.getUsername())
                    .withClaim("id", user.getId())
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
