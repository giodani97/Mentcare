package univr.mentcare.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;



public class Paziente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cognome;
    private String nome;
    private Date dataDiNascita;
    private String comuneNascita;
    private String provinciaNascita;
    private String nazionalita;
    private String nTelefono;
    private String diagnosi;
    private Date dataDiagnosi;
    private boolean pericoloso;
    private boolean autosufficiente;

    public Paziente(String cognome, String nome, Date dataDiNascita, String comuneNascita, String provinciaNascita, String nazionalita, String nTelefono, boolean pericoloso, boolean autosufficiente) {
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataDiNascita;
        this.comuneNascita = comuneNascita;
        this.provinciaNascita = provinciaNascita;
        this.nazionalita = nazionalita;
        this.nTelefono = nTelefono;
        this.pericoloso = pericoloso;
        this.autosufficiente = autosufficiente;
    }

    public long getId() {
        return id;
    }


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public String getProvinciaNascita() {
        return provinciaNascita;
    }

    public void setProvinciaNascita(String provinciaNascita) {
        this.provinciaNascita = provinciaNascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getnTelefono() {
        return nTelefono;
    }

    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public Date getDataDiagnosi() {
        return dataDiagnosi;
    }

    public void setDataDiagnosi(Date dataDiagnosi) {
        this.dataDiagnosi = dataDiagnosi;
    }

    public boolean isPericoloso() {
        return pericoloso;
    }

    public void setPericoloso(boolean pericoloso) {
        this.pericoloso = pericoloso;
    }

    public boolean isAutosufficiente() {
        return autosufficiente;
    }

    public void setAutosufficiente(boolean autosufficiente) {
        this.autosufficiente = autosufficiente;
    }

    public void getCartellaClinica() {
        // TODO implement here
    }

}
