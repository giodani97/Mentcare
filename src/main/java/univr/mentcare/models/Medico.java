package univr.mentcare.models;

import javax.persistence.*;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "MEDICO_ID")
    private long id;
    private String nome;
    private String cognome;
    private String iscrOrdineMedici;

    protected Medico(){}

    public Medico(String nome, String cognome, String iscrOrdineMedici) {
        this.nome = nome;
        this.cognome = cognome;
        this.iscrOrdineMedici = iscrOrdineMedici;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIscrOrdineMedici() {
        return iscrOrdineMedici;
    }

    public void setIscrOrdineMedici(String iscrOrdineMedici) {
        this.iscrOrdineMedici = iscrOrdineMedici;
    }
}
