package univr.mentcare.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Farmaco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeCommerciale;
    private String principioAttivo;

    public Farmaco(String nomeCommerciale, String principioAttivo) {
        this.nomeCommerciale = nomeCommerciale;
        this.principioAttivo = principioAttivo;
    }

    public long getId() {
        return id;
    }
    public String getNomeCommerciale() {
        return nomeCommerciale;
    }

    public void setNomeCommerciale(String value) {
        nomeCommerciale = value;
    }

    public String getPrincipioAttivo() {
        return principioAttivo;
    }

    public void setPrincipioAttivo(String value) {
        principioAttivo = value;
    }

}
