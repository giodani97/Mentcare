package univr.mentcare.Repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.Models.Medico;

import java.util.List;

public interface MedicoRepository extends CrudRepository<Medico, Long> {
    List<Medico> findByCognome(String cognome);
    Medico findById(long id);
    Medico findByIscrOrdineMedici(String iscrOrdineMedici);
}