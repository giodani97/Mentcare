package univr.mentcare.modelsTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import univr.mentcare.models.Medico;


public class MedicoTest {

    private Medico medico;

    @Before
    public void creaMedico(){
        medico = new Medico("Mario", "Rossi", "VR001");
    }

    @Test
    public void getId(){
        assertEquals(0, medico.getId());
    }

    @Test
    public void getIscrOrdineMediciTest(){
        assertEquals("VR001", medico.getIscrOrdineMedici());
    }

    @Test
    public void setIscrOrdiniMediciTest(){
        medico.setIscrOrdineMedici("VI001");
        assertEquals("VI001", medico.getIscrOrdineMedici());
    }

    @Test
    public void testGetCognome(){
        assertEquals("Rossi", medico.getCognome());
    }

    @Test
    public void testSetCognome(){
        medico.setCognome("Bianchi");
        assertEquals("Bianchi", medico.getCognome());
    }
    @Test
    public void testGetNome(){
        assertEquals("Mario", medico.getNome());
    }

    @Test
    public void testSetNome(){
        medico.setNome("Luigi");
        assertEquals("Luigi", medico.getNome());
    }

}
