package univr.mentcare.Repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.Models.Medico;
import univr.mentcare.Models.Paziente;
import univr.mentcare.Models.Visita;

import java.util.Date;
import java.util.List;

public interface VisitaRepository extends CrudRepository<Visita, Long> {
    Visita getVisitaByIdVisita(long id);
    List<Visita> getVisitaByMedicoOrderByDataVisita(Medico medico);
    List<Visita> getVisitaByPaziente(Paziente paziente);
}
