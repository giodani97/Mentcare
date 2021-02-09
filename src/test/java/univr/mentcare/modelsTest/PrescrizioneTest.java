package univr.mentcare.modelsTest;

import org.junit.Before;
import org.junit.Test;
import univr.mentcare.models.Farmaco;
import univr.mentcare.models.Medico;
import univr.mentcare.models.Paziente;
import univr.mentcare.models.Prescrizione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PrescrizioneTest {

    private Prescrizione prescrizione;
    private Medico medico;
    private Farmaco farmaco;
    private Paziente paziente;

    @Before
    public void setUp() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datanascita = formatter.parse("01/01/1980");
        farmaco = new Farmaco("Haldol", "aloperidolo");
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
        prescrizione = new Prescrizione(farmaco, Calendar.getInstance().getTime(), "50mg", medico, paziente);
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

    @Test
    public void getMedicoPrescrittore() {
        assertEquals(medico.getIscrOrdineMedici(), prescrizione.getMedicoPrescrittore().getIscrOrdineMedici());
    }

    @Test
    public void setMedicoPrescrittore() {
        Medico nuovoMedico = new Medico("Lugi", "Mario", "VI111");
        prescrizione.setMedicoPrescrittore(nuovoMedico);
        assertEquals(nuovoMedico.getIscrOrdineMedici(), prescrizione.getMedicoPrescrittore().getIscrOrdineMedici());
    }

    @Test
    public void getPaziente(){
        assertEquals(paziente.getCognome(), prescrizione.getPaziente().getCognome());
    }

    @Test
    public void setPaziente(){
        Paziente nuovoPaziente = new Paziente("Neri",
                "Rossi",
                Calendar.getInstance().getTime(),
                "Verona",
                "Verona",
                "Italia",
                "09876543210",
                true,
                false);
        prescrizione.setPaziente(nuovoPaziente);
        assertEquals(nuovoPaziente.getCognome(), prescrizione.getPaziente().getCognome());
    }
}