package univr.mentcare.ModelsTest;

import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import univr.mentcare.Models.Medico;


public class MedicoTest {

    private Medico medico;

    @BeforeEach
    public void creaMedico(){
        medico = new Medico("Mario", "Rossi", "VR001");
    }

    @Test
    public void testGetID(){
        assertEquals("VR001", medico.getIscrOrdineMedici());
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
