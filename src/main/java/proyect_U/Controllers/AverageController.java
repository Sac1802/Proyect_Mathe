package proyect_U.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proyect_U.Model.Averages;
import proyect_U.Repository.AverageRepository;

import java.net.URI;
import java.util.Optional;

@RestController
public class AverageController {

    private final AverageRepository averageRepository;

    public AverageController(AverageRepository averageRepository){
        this.averageRepository = averageRepository;
    }

    @GetMapping("/getId/averages/{id}")
    public ResponseEntity<Averages> getByIdAverages(@PathVariable Long id){
        ResponseEntity<Averages> response;
        Optional<Averages> optional = averageRepository.findById(id);
        if (optional.isEmpty()){
            response = ResponseEntity.ok(optional.get());
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @PostMapping("/save/average")
    public ResponseEntity<Averages> saveAverage(@RequestBody Averages averages, UriComponentsBuilder
            uriComponentsBuilder){
        ResponseEntity<Averages> response;
        if(averages.getValues().isEmpty()){
            response = ResponseEntity.unprocessableEntity().build();
        }else {
            averageRepository.save(averages);
            URI url = uriComponentsBuilder.path("/register/getId/{id}").buildAndExpand(
                    averages.getId()).toUri();
            response = ResponseEntity.created(url).body(averages);
        }
        return response;
    }
}
