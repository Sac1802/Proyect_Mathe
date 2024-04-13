package proyect_U.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyect_U.Model.Register;


@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findByUsername(String username);
    Register findByPassword(String password);
}
