package univr.mentcare.repositories;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.models.Medico;

import java.util.List;

public interface MedicoRepository extends CrudRepository<Medico, Long> {
    List<Medico> findByCognome(String cognome);
    Medico findById(long id);
    Medico findByIscrOrdineMedici(String iscrOrdineMedici);
}
