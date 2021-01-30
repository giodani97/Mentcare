package univr.mentcare.ModelsTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import univr.mentcare.Models.Paziente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PazienteTest {

    private Paziente paziente;

    @BeforeEach
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        paziente.setDataDiNascita(Calendar.getInstance().getTime());
        Date datanascita = paziente.getDataDiNascita();
        assertEquals(format.format(Calendar.getInstance().getTime()), format.format(datanascita));
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
    public void getDataDiagnosi() {
    }

    @Test
    public void setDataDiagnosi() {
    }

    @Test
    public void isPericoloso() {
    }

    @Test
    public void setPericoloso() {
    }

    @Test
    public void isAutosufficiente() {
    }

    @Test
    public void setAutosufficiente() {
    }

    @Test
    public void getCartellaClinica() {
    }


}
