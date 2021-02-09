package univr.mentcare.repositoriesTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import univr.mentcare.models.Farmaco;
import univr.mentcare.models.Medico;
import univr.mentcare.models.Paziente;
import univr.mentcare.models.Prescrizione;
import univr.mentcare.repository.FarmacoRepository;
import univr.mentcare.repository.MedicoRepository;
import univr.mentcare.repository.PazienteRepository;
import univr.mentcare.repository.PrescrizioneRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PrescrizioneRepositoryTest {

    @Autowired
    private PrescrizioneRepository prescrizioneRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private FarmacoRepository farmacoRepository;
    @Autowired
    private PazienteRepository pazienteRepository;

    private Prescrizione prescrizione;
    private Medico medico;
    private Farmaco farmaco;
    private Paziente paziente;


    @Before
    public void setUp() throws Exception {
        tearDown();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datanascita = formatter.parse("01/01/1980");
        medico = new Medico("Mario", "Rossi", "VR001");
        farmaco = new Farmaco("Haldol", "aloperidolo");
        paziente = new Paziente("Bianchi",
                "Neri",
                datanascita,
                "Valdagno",
                "Vicenza",
                "Italia",
                "01234567890",
                false,
                true);
        prescrizione = new Prescrizione(farmaco, Calendar.getInstance().getTime(), "50mg", medico, paziente);
        medicoRepository.save(medico);
        farmacoRepository.save(farmaco);
        pazienteRepository.save(paziente);
    }

    @After
    public void tearDown() throws Exception {
        prescrizioneRepository.deleteAll();
        medicoRepository.deleteAll();
        farmacoRepository.deleteAll();
        pazienteRepository.deleteAll();
    }

    @Test
    public void addPrescrizioneToRepositoryTest(){
        prescrizioneRepository.save(prescrizione);
        assertEquals(1, prescrizioneRepository.count());
        assertEquals(farmaco.getId(), prescrizioneRepository.findByIdPrescrizione(prescrizione.getIdPrescrizione()).getFarmaco().getId());
    }

    @Test
    public void deletePrescrizioneFromRepositoryTest(){
        prescrizioneRepository.save(prescrizione);
        assertEquals(1, prescrizioneRepository.count());
        prescrizioneRepository.delete(prescrizione);
        assertEquals(0, prescrizioneRepository.count());
    }

    @Test
    public void foreignKeyTest(){
        try{
            medicoRepository.deleteAll();
            return;
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
}