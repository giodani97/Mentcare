package univr.mentcare.repositoriesTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import univr.mentcare.models.Farmaco;
import univr.mentcare.repositories.FarmacoRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class FarmacoRepositoryTest {

    @Autowired
    private FarmacoRepository farmacoRepository;

    private Farmaco farmaco;

    @Before
    public void setUp() throws Exception {
        tearDown();
        farmaco = new Farmaco("Haldol", "aloperidolo");
    }

    @After
    public void tearDown() throws Exception {
        farmacoRepository.deleteAll();
    }

    @Test
    public void addFarmacoToRepository(){
        farmacoRepository.save(farmaco);
        assertEquals("Haldol", farmacoRepository.findById(farmaco.getId()).getNomeCommerciale());
    }

    @Test
    public void deleteFarmacoFromRepository(){
        farmacoRepository.save(farmaco);
        assertEquals("Haldol", farmacoRepository.findById(farmaco.getId()).getNomeCommerciale());
        farmacoRepository.delete(farmaco);
        assertNull(farmacoRepository.findById(farmaco.getId()));
    }
}