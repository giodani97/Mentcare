package univr.mentcare.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String iscrOrdineMedici;

    protected Medico(){};

    public Medico(String nome, String cognome, String iscrOrdineMedici) {
        this.nome = nome;
        this.cognome = cognome;
        this.iscrOrdineMedici = iscrOrdineMedici;
    }

    public Long getId() {
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
