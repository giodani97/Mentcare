package univr.mentcare.repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.models.Farmaco;

public interface FarmacoRepository extends CrudRepository<Farmaco, Long> {
    Farmaco findById(long id);
}
