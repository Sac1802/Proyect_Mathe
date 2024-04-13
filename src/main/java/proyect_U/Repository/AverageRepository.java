package proyect_U.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyect_U.Model.Averages;

public interface AverageRepository extends JpaRepository<Averages, Long> {
}
