package proyect_U.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proyect_U.Model.Register;
import proyect_U.Repository.RegisterRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RegisterController {

    private final RegisterRepository registerRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(RegisterRepository registerRepository){
        this.registerRepository = registerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/register/get")
    public List<Register> findAll(){
        return registerRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/register/getId/{id}")
    public ResponseEntity<Register> finById(@PathVariable Long id){
        Optional<Register> registerOptional = registerRepository.findById(id);
        ResponseEntity<Register> response;
        if (registerOptional.isEmpty()){
            response = ResponseEntity.ok(registerOptional.get());
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register/user")
    @Transactional
    public ResponseEntity<Register> saveRegister(@RequestBody Register register, UriComponentsBuilder
                                                 uriComponentsBuilder){
        String encoderPassword = this.passwordEncoder.encode(register.getPassword());
        ResponseEntity<Register> response;
        if (register.getUsername().isEmpty()){
            response = ResponseEntity.unprocessableEntity().build();
        }else {
            register.setPassword(encoderPassword);
            registerRepository.save(register);
            URI url = uriComponentsBuilder.path("/register/getId/{id}").buildAndExpand(
                    register.getId()).toUri();
            response = ResponseEntity.created(url).body(register);
        }
        return response;
    }

    /*@PutMapping("/register/update/{id}")
    @Transactional
    public ResponseEntity<Register> updateRegister(@RequestBody Register register, @PathVariable
                                                   Long id){
        Optional<Register> response = registerRepository.findById(id);
        //if ()
        return Register;
    }*/
}
