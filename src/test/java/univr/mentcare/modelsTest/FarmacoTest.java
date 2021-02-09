package univr.mentcare.modelsTest;

import org.junit.Before;
import org.junit.Test;
import univr.mentcare.models.Farmaco;

import static org.junit.Assert.*;

public class FarmacoTest {

    private Farmaco farmaco;

    @Before
    public void setUp(){
        farmaco = new Farmaco("Haldol", "aloperidolo");
    }

    @Test
    public void getId() {
        assertEquals(0, farmaco.getId());
    }

    @Test
    public void getNomeCommerciale() {
        assertEquals("Haldol", farmaco.getNomeCommerciale());
    }

    @Test
    public void setNomeCommerciale() {
        farmaco.setNomeCommerciale("Zyprexa");
        assertEquals("Zyprexa", farmaco.getNomeCommerciale());
    }

    @Test
    public void getPrincipioAttivo() {
        assertEquals("aloperidolo", farmaco.getPrincipioAttivo());
    }

    @Test
    public void setPrincipioAttivo() {
        farmaco.setPrincipioAttivo("olanzapina");
        assertEquals("olanzapina", farmaco.getPrincipioAttivo());
    }
}