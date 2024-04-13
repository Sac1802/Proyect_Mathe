package proyect_U.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proyect_U.Model.Usuarios;
import proyect_U.Repository.UsuarioRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    public List<Usuarios> findAll(){
        return usuarioRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getId/{id}")
    public ResponseEntity<Usuarios> findById(@PathVariable Long id){
        Optional<Usuarios> usuariosOptional = usuarioRepository.findById(id);
        ResponseEntity<Usuarios> response;
        if (usuariosOptional.isEmpty()){
            response = ResponseEntity.ok(usuariosOptional.get());
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save/user")
    @Transactional
    public ResponseEntity<Usuarios> create(@RequestBody Usuarios usuarios, UriComponentsBuilder uriComponentsBuilder){
        usuarioRepository.save(usuarios);
        Usuarios getUrlUser = new Usuarios(usuarios.getId(), usuarios.getName(),
                usuarios.getLastName(), usuarios.getBirthDate(), usuarios.getWeight(),
                usuarios.getHeight(), usuarios.getIdRegister());
        URI url = uriComponentsBuilder.path("/getId/{id}").buildAndExpand(usuarios.getId()).toUri();
        return ResponseEntity.created(url).body(usuarios);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/user/{id}")
    @Transactional
    public ResponseEntity<Usuarios> update(@RequestBody Usuarios usuarios, @PathVariable Long id){
        Optional<Usuarios> usuariosOptional = usuarioRepository.findById(id);
        ResponseEntity<Usuarios> response;
        if (usuariosOptional.isEmpty()){
            usuarioRepository.save(usuarios);
            response = ResponseEntity.ok(usuarios);
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}
