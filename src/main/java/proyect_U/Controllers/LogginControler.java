package proyect_U.Controllers;

import com.auth0.jwt.JWT;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import proyect_U.Model.Register;
import proyect_U.Repository.RegisterRepository;
import proyect_U.Security.Filter.JsonWebToken;

@RestController
public class LogginControler {

    private JsonWebToken jsonWebToken;

    private final PasswordEncoder passwordEncoder;

    private final RegisterRepository registerRepository;

    public LogginControler(RegisterRepository registerRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.registerRepository = registerRepository;
        this.jsonWebToken = new JsonWebToken(registerRepository);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Login")
    @Transactional
    public ResponseEntity<Register> login(@RequestBody Register register ){
        String Encoder = passwordEncoder.encode(register.getPassword());
        Register user = registerRepository.findByUsername(register.getUsername());
        ResponseEntity.ok().body("");
        ResponseEntity response;
        if (user != null && passwordEncoder.matches(register.getPassword(), user.getPassword())){
            String token = jsonWebToken.generate(register);
            response = ResponseEntity.ok().body("Bearer:" + token + " Id:" + user.getId());
        }else {
            response = ResponseEntity.noContent().build();
        }

        return response;
    }

    /*@PostMapping("/invalid")
    @Transactional
    public ResponseEntity<String> prueba(@RequestBody String token){
        ResponseEntity response = jsonWebToken.validateJWT(token);
        return response;
    }*/
}
