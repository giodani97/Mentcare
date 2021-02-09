package univr.mentcare.repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.models.Medico;
import univr.mentcare.models.Paziente;
import univr.mentcare.models.Visita;

import java.util.List;

public interface VisitaRepository extends CrudRepository<Visita, Long> {
    Visita getVisitaByIdVisita(long id);
    List<Visita> getVisitaByMedicoOrderByDataVisita(Medico medico);
    List<Visita> getVisitaByPazienteOrderByDataVisita(Paziente paziente);
}
