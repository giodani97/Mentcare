package univr.mentcare.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Prescrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPrescrizione;
    private Farmaco farmaco;
    private Date dataPrescrizione;
    private String dosaggio;

    public Prescrizione(Farmaco farmaco, Date dataPrescrizione, String dosaggio) {
        this.farmaco = farmaco;
        this.dataPrescrizione = dataPrescrizione;
        this.dosaggio = dosaggio;
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
}
