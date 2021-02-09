package univr.mentcare.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Prescrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "PRESCRIZIONE_ID")
    private long idPrescrizione;
    @ManyToOne(targetEntity = Medico.class)
    @JoinColumn(name = "MEDICO_ID")
    private Medico medicoPrescrittore;
    @ManyToOne(targetEntity = Farmaco.class)
    @JoinColumn(name = "FARMACO_ID")
    private Farmaco farmaco;
    @ManyToOne(targetEntity = Paziente.class)
    @JoinColumn(name = "PAZIENTE_ID")
    private Paziente paziente;
    private Date dataPrescrizione;
    private String dosaggio;

    protected Prescrizione(){}

    public Prescrizione(Farmaco farmaco, Date dataPrescrizione, String dosaggio, Medico medicoPrescrittore, Paziente paziente) {
        this.farmaco = farmaco;
        this.dataPrescrizione = dataPrescrizione;
        this.dosaggio = dosaggio;
        this.medicoPrescrittore = medicoPrescrittore;
        this.paziente = paziente;
    }

    public long getIdPrescrizione() {
        return idPrescrizione;
    }

    public Date getDataPrescrizione() {
        return dataPrescrizione;
    }

    public void setDataPrescrizione(Date value) {
        dataPrescrizione = value;
    }

    public String getDosaggio() {
        return dosaggio;
    }

    public void setDosaggio(String value) {
        dosaggio = value;
    }

    public Farmaco getFarmaco(){
        return farmaco;
    }

    public void setFarmaco(Farmaco value){
        this.farmaco = value;
    }

    public Medico getMedicoPrescrittore() {
        return medicoPrescrittore;
    }

    public void setMedicoPrescrittore(Medico medicoPrescrittore) {
        this.medicoPrescrittore = medicoPrescrittore;
    }

    public Paziente getPaziente(){
        return paziente;
    }

    public void setPaziente(Paziente value){
        this.paziente = value;
    }
}
