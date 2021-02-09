package univr.mentcare.repositoriesTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import univr.mentcare.models.Medico;
import univr.mentcare.repository.MedicoRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    private Medico medico;

    @Before
    public void setUp() throws Exception {
        tearDown();
        medico = new Medico("Mario", "Rossi", "VR001");
    }

    @After
    public void tearDown() throws Exception {
        medicoRepository.deleteAll();
    }

    @Test
    public void addMedicoToRepository(){
        medicoRepository.save(medico);
        assertEquals(medico.getIscrOrdineMedici(), medicoRepository.findById(medico.getId()).getIscrOrdineMedici());
        assertEquals(medico.getIscrOrdineMedici(), medicoRepository.findByIscrOrdineMedici(medico.getIscrOrdineMedici()).getIscrOrdineMedici());
        assertEquals(medico.getIscrOrdineMedici(), medicoRepository.findByCognome(medico.getCognome()).get(0).getIscrOrdineMedici());
    }

    @Test
    public void deleteMedicoFromRepository(){
        medicoRepository.save(medico);
        assertTrue(0 < medicoRepository.count());
        medicoRepository.delete(medico);
        assertTrue(0 == medicoRepository.count());
    }
}