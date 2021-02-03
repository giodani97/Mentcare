package univr.mentcare.RepositoriesTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import univr.mentcare.Models.Paziente;
import univr.mentcare.Repository.PazienteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PazienteRepositoryTest {

    @Autowired
    private PazienteRepository pazienteRepository;
    private Paziente paziente;

    @Before
    public void setUp() throws Exception {
        tearDown();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datanascita = formatter.parse("01/01/1980");
        paziente = new Paziente("Bianchi",
                "Neri",
                datanascita,
                "Valdagno",
                "Vicenza",
                "Italia",
                "01234567890",
                false,
                true);
    }

    @Test
    public void addPazienteToRepository(){
        pazienteRepository.save(paziente);
        assertEquals(paziente.getCognome(), pazienteRepository.getPazienteById(paziente.getId()).getCognome());
        assertEquals(1, pazienteRepository.count());
    }

    public void deletePazienteFromRepository(){
        pazienteRepository.save(paziente);
        assertEquals(1, pazienteRepository.count());
        pazienteRepository.delete(paziente);
        assertEquals(0, pazienteRepository.count());
    }


    @After
    public void tearDown() throws Exception {
        pazienteRepository.deleteAll();
    }
}