package univr.mentcare.repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.models.Farmaco;
import univr.mentcare.models.Paziente;
import univr.mentcare.models.Prescrizione;

import java.util.List;

public interface PrescrizioneRepository extends CrudRepository<Prescrizione, Long> {
    List<Prescrizione> findByFarmaco(Farmaco farmaco);
    Prescrizione findByIdPrescrizione(long id);
    List<Prescrizione> findByPazienteOrderByDataPrescrizione(Paziente paziente);
}
