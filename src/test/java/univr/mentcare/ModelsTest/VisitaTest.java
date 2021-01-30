package univr.mentcare.ModelsTest;

import org.junit.Before;
import org.junit.Test;
import univr.mentcare.Models.Medico;
import univr.mentcare.Models.Paziente;
import univr.mentcare.Models.Visita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.junit.Assert.*;


public class VisitaTest {

    private Visita visita;
    private Medico medico;
    private Paziente paziente;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        medico = new Medico("Mario", "Rossi", "VR001");
        paziente = new Paziente("Bianchi", "Neri", format.parse("10/10/1980"), "Valdagno", "Vicenza", "Italia", "01234567890", false, true);
        this.visita = new Visita(medico, paziente, Calendar.getInstance().getTime());
    }

    @Test
    public void getIdVisita() {
        assertEquals(0, visita.getIdVisita());
    }

    @Test
    public void getMedico() {
        assertEquals(medico.getIscrOrdineMedici(), visita.getMedico().getIscrOrdineMedici());
    }

    @Test
    public void setMedico() {
        Medico nuovoMedico = new Medico("Luigi", "Rossi", "VR002");
        visita.setMedico(nuovoMedico);
        assertEquals(nuovoMedico.getIscrOrdineMedici(), visita.getMedico().getIscrOrdineMedici());
    }

    @Test
    public void getPaziente() {
        assertEquals(paziente.getCognome(), visita.getPaziente().getCognome());
    }

    @Test
    public void setIdPaziente() throws ParseException {
        Paziente nuovoPaziente = new Paziente("Parenti", "Verdi", format.parse("10/10/1980"), "Valdagno", "Vicenza", "Italia", "01234567890", false, true);
        visita.setPaziente(nuovoPaziente);
        assertEquals(nuovoPaziente.getId(), visita.getPaziente().getId());
    }

    @Test
    public void getDataVisita() {
        assertEquals(Calendar.getInstance().getTime(), visita.getDataVisita());
    }

    @Test
    public void setDataVisita() throws ParseException {
        visita.setDataVisita(format.parse("10/10/2010"));
        assertEquals(format.parse("10/10/2010"), visita.getDataVisita());
    }

    @Test
    public void getOsservazioni() {
        assertNull(visita.getOsservazioni());
    }

    @Test
    public void setOsservazioni() {
        visita.setOsservazioni("Prova di osservazione");
        assertEquals("Prova di osservazione", visita.getOsservazioni());
    }
}
