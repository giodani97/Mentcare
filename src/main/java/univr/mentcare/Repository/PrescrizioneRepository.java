package univr.mentcare.Repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.Models.Farmaco;
import univr.mentcare.Models.Paziente;
import univr.mentcare.Models.Prescrizione;

import java.util.List;

public interface PrescrizioneRepository extends CrudRepository<Prescrizione, Long> {
    List<Prescrizione> findByFarmaco(Farmaco farmaco);
    Prescrizione findByIdPrescrizione(long id);
    Prescrizione findByPaziente(Paziente paziente);
}
