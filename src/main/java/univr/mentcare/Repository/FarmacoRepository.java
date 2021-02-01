package univr.mentcare.Repository;

import org.springframework.data.repository.CrudRepository;
import univr.mentcare.Models.Farmaco;

public interface FarmacoRepository extends CrudRepository<Farmaco, Long> {
    Farmaco findById(long id);
}
