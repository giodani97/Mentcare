package univr.mentcare.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "VISITA_ID")
    private long idVisita;
    @ManyToOne(targetEntity = Medico.class)
    @JoinColumn(name = "MEDICO_ID")
    private Medico medico;
    @ManyToOne(targetEntity = Paziente.class)
    @JoinColumn(name = "PAZIENTE_ID")
    private Paziente paziente;
    private Date dataVisita;
    private String osservazioni;

    protected Visita(){};

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
