package univr.mentcare.modelsTest;


import org.junit.Before;
import org.junit.Test;
import univr.mentcare.models.Paziente;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PazienteTest {

    private Paziente paziente;

    @Before
    public void creaPazienteTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date datanascita = formatter.parse("01/01/1980");
        paziente = new Paziente("Bianchi",
                "Neri",
                datanascita,
                "Valdagno",
                "Vicenza",
                "Italia",
                "01234567890",
                false,
                true);
    }

    @Test
    public void getIdTest(){
        assertEquals(0, paziente.getId());
    }

    @Test
    public void getCognomeTest() {
        assertEquals("Bianchi", paziente.getCognome());
    }

    @Test
    public void setCognomeTest() {
        paziente.setCognome("Rossi");
        assertEquals("Rossi", paziente.getCognome());
    }

    @Test
    public void getNomeTest() {
        assertEquals("Neri", paziente.getNome());
    }

    @Test
    public void setNomeTest() {
        paziente.setNome("Giorgio");
        assertEquals("Giorgio", paziente.getNome());
    }

    @Test
    public void getDataDiNascitaTest() {
        Date datanascita = paziente.getDataDiNascita();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/01/1980", format.format(datanascita));
    }

    @Test
    public void setDataDiNascitaTest() {
        Date newDate = Calendar.getInstance().getTime();
        paziente.setDataDiNascita(newDate);
        assertEquals(newDate, paziente.getDataDiNascita());
    }

    @Test
    public void getComuneNascitaTest() {
        assertEquals("Valdagno", paziente.getComuneNascita());
    }

    @Test
    public void setComuneNascitaTest() {
        paziente.setComuneNascita("Milano");
        assertEquals("Milano", paziente.getComuneNascita());
    }

    @Test
    public void getProvinciaNascitaTestTest() {
        assertEquals("Vicenza", paziente.getProvinciaNascita());
    }

    @Test
    public void setProvinciaNascitaTest() {
        paziente.setProvinciaNascita("Milano");
        assertEquals("Milano", paziente.getProvinciaNascita());
    }

    @Test
    public void getNazionalitaTest() {
        assertEquals("Italia", paziente.getNazionalita());
    }

    @Test
    public void setNazionalitaTest() {
        paziente.setNazionalita("Germania");
        assertEquals("Germania", paziente.getNazionalita());
    }

    @Test
    public void getnTelefonoTest() {
        assertEquals("01234567890",paziente.getnTelefono());
    }

    @Test
    public void setnTelefonoTest() {
        paziente.setnTelefono("09876543210");
        assertEquals("09876543210", paziente.getnTelefono());
    }

    @Test
    public void getDiagnosiTest() {
        assertNull(paziente.getDiagnosi());
    }

    @Test
    public void setDiagnosiTest() {
        paziente.setDiagnosi("Il paziente è stato diagnosticato con schizofrenia");
        assertEquals("Il paziente è stato diagnosticato con schizofrenia", paziente.getDiagnosi());
    }

    @Test
    public void getDataDiagnosiTest() {
        assertNull(paziente.getDataDiagnosi());
    }

    @Test
    public void setDataDiagnosiTest() {
        Date newDate = Calendar.getInstance().getTime();
        paziente.setDataDiagnosi(newDate);
        assertEquals(newDate, paziente.getDataDiagnosi());
    }

    @Test
    public void isPericolosoTest() {
        assertFalse(paziente.isPericoloso());
    }

    @Test
    public void setPericolosoTest() {
        paziente.setPericoloso(true);
        assertTrue(paziente.isPericoloso());
    }

    @Test
    public void isAutosufficienteTest() {
        assertTrue(paziente.isAutosufficiente());
    }

    @Test
    public void setAutosufficienteTest() {
        paziente.setAutosufficiente(false);
        assertFalse(paziente.isAutosufficiente());
    }


}
