package univr.mentcare.ModelsTest;

import org.junit.Before;
import org.junit.Test;
import univr.mentcare.Models.Farmaco;
import univr.mentcare.Models.Prescrizione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PrescrizioneTest {

    private Prescrizione prescrizione;

    @Before
    public void setUp() throws Exception {
        prescrizione = new Prescrizione(new Farmaco("Haldol", "aloperidolo"), Calendar.getInstance().getTime(), "50mg");
    }

    @Test
    public void getIdPrescrizioneTest() {
        assertEquals(0, prescrizione.getIdPrescrizione());
    }

    @Test
    public void getDataPrescrizioneTest() {
        assertEquals(Calendar.getInstance().getTime(), prescrizione.getDataPrescrizione());
    }

    @Test
    public void setDataPrescrizioneTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date newdate = format.parse("10/10/2010");
        prescrizione.setDataPrescrizione(newdate);
        assertEquals(newdate, prescrizione.getDataPrescrizione());
    }

    @Test
    public void getDosaggioTest() {
        assertEquals("50mg", prescrizione.getDosaggio());
    }

    @Test
    public void setDosaggioTest() {
        prescrizione.setDosaggio("100g");
        assertEquals("100g", prescrizione.getDosaggio());
    }

    @Test
    public void getFarmacoTest(){
        assertEquals("Haldol", prescrizione.getFarmaco().getNomeCommerciale());
    }
    @Test
    public void setFarmacoTest(){
        Farmaco nuovoFarmaco = new Farmaco("Zyprexa", "aloperidolo");
        prescrizione.setFarmaco(nuovoFarmaco);
        assertEquals(nuovoFarmaco.getPrincipioAttivo(), prescrizione.getFarmaco().getPrincipioAttivo());
        assertEquals(nuovoFarmaco.getNomeCommerciale(), prescrizione.getFarmaco().getNomeCommerciale());
    }
}