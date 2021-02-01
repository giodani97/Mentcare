package univr.mentcare.Models;

import javax.persistence.*;

@Entity
public class Farmaco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "FARMACO_ID")
    private long id;
    private String nomeCommerciale;
    private String principioAttivo;

    protected Farmaco() {
    }

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
