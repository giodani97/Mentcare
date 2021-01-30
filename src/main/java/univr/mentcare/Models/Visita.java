package univr.mentcare.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idVisita;
    private Medico medico;
    private Paziente paziente;
    private Date dataVisita;
    private String osservazioni;

    public Visita(Medico medico, Paziente paziente, Date dataVisita) {
        this.medico = medico;
        this.paziente = paziente;
        this.dataVisita = dataVisita;
    }

    public long getIdVisita() {
        return idVisita;
    }
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico value) {
        medico = value;
    }
    public Paziente getPaziente() {
        return paziente;
    }
    public void setPaziente(Paziente value) {
        paziente = value;
    }
    public Date getDataVisita() {
        return dataVisita;
    }
    public void setDataVisita(Date value) {
        dataVisita = value;
    }
    public String getOsservazioni() {
        return osservazioni;
    }
    public void setOsservazioni(String value) {
        osservazioni = value;
    }

}
