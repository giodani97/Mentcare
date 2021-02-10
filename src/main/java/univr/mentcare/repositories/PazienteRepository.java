package univr.mentcare.repositories;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.models.Paziente;

import java.util.List;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {
    List<Paziente> getPazienteByCognome(String cognome);
    Paziente getPazienteById(Long id);
    Paziente getPazienteByCognomeAndNome(String cognome, String nome);
}
