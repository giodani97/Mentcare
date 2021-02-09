package univr.mentcare.repositoriesTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import univr.mentcare.models.*;
import univr.mentcare.repository.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VisitaRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PazienteRepository pazienteRepository;
    @Autowired
    private VisitaRepository visitaRepository;

    private Medico medico;
    private Paziente paziente;
    private Visita visita;

    @Before
    public void setUp() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datanascita = formatter.parse("01/01/1980");
        medico = new Medico("Mario", "Rossi", "VR001");
        paziente = new Paziente("Bianchi",
                "Neri",
                datanascita,
                "Valdagno",
                "Vicenza",
                "Italia",
                "01234567890",
                false,
                true);
        visita = new Visita(medico, paziente, Calendar.getInstance().getTime());
        medicoRepository.save(medico);
        pazienteRepository.save(paziente);
    }

    @Test
    public void addVisitaToRepository(){
        visitaRepository.save(visita);
        assertEquals(1, visitaRepository.count());
        assertEquals(visita.getIdVisita(), visitaRepository.getVisitaByIdVisita(visita.getIdVisita()).getIdVisita());
    }

    @Test
    public void deleteVisitaFromRepository(){
        assertEquals(0, visitaRepository.count());
        visitaRepository.save(visita);
        assertEquals(1, visitaRepository.count());
        visitaRepository.delete(visita);
        assertEquals(0, visitaRepository.count());
    }

    @Test
    public void getVisitaByMedicoTest(){
        List<Visita> visite = visitaRepository.getVisitaByMedicoOrderByDataVisita(medico);
        if(visite.size() == 1)
            assertEquals(visita.getIdVisita(), visite.get(0).getIdVisita());
    }

    @Test
    public void getVisitaByPazienteTest(){
        List<Visita> visite = visitaRepository.getVisitaByPazienteOrderByDataVisita(paziente);
        if(visite.size() == 1)
            assertEquals(visita.getIdVisita(), visite.get(0).getIdVisita());
    }

    @After
    public void tearDown() throws Exception {
        visitaRepository.deleteAll();
        medicoRepository.deleteAll();
        pazienteRepository.deleteAll();
    }
}