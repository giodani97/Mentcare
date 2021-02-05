package univr.mentcare.Controller;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import univr.mentcare.Models.*;
import univr.mentcare.Repository.*;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ControllerMentcare {

    private Medico loggedMedico;

    @Autowired
    private FarmacoRepository farmacoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PazienteRepository pazienteRepository;
    @Autowired
    private PrescrizioneRepository prescrizioneRepository;
    @Autowired
    private VisitaRepository visitaRepository;

    @Component
    @Profile("!test")
    private class InitRepositories {
        MedicoRepository medicoRepository;
        PazienteRepository pazienteRepository;
        VisitaRepository visitaRepository;
        FarmacoRepository farmacoRepository;
        InitRepositories(MedicoRepository medicoRepository, PazienteRepository pazienteRepository, VisitaRepository visitaRepository, FarmacoRepository farmacoRepository) throws ParseException {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            loggedMedico = new Medico("Mario", "Rossi", "VR001");
            this.medicoRepository = medicoRepository;
            this.medicoRepository.save(loggedMedico);
            this.pazienteRepository = pazienteRepository;
            Paziente paziente = new Paziente("Bianchi", "Neri", format.parse("10/10/1980"), "Valdagno", "Vicenza", "Italia", "01234567890", true, false);
            paziente.setDiagnosi("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do eiusmod tempor incidunt ut labore et dolore magna aliqua.");
            paziente.setDataDiagnosi(Calendar.getInstance().getTime());
            Paziente paziente2 = new Paziente("Luigi", "Verdi", format.parse("21/12/1962"), "Roma", "Roma", "Italia", "3697412682", false, true);
            this.pazienteRepository.save(paziente);
            this.pazienteRepository.save(paziente2);
            this.visitaRepository = visitaRepository;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 1);
            Date date = cal.getTime();
            this.visitaRepository.save(new Visita(loggedMedico, paziente2, date));
            cal.add(Calendar.HOUR, 23);
            this.visitaRepository.save(new Visita(loggedMedico, paziente, cal.getTime()));
            this.visitaRepository.save(new Visita(loggedMedico, paziente, Calendar.getInstance().getTime()));
            this.farmacoRepository = farmacoRepository;
            this.farmacoRepository.save(new Farmaco("Haldol", "aloperidolo"));
            this.farmacoRepository.save(new Farmaco("Zyprexa", "olanzapina"));
        }
    }

    private void creaReport(File file, Paziente paziente) throws Docx4JException, FileNotFoundException {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
        mdp.addParagraphOfText("Report del paziente " + paziente.getCognome() + " " + paziente.getNome());
        mdp.addParagraphOfText("Cognome: " + paziente.getCognome() + "\nNome: " + paziente.getNome() +
                "\nData di nascita: " + format.format(paziente.getDataDiNascita()) + "\nNato a: " + paziente.getComuneNascita()
                + ", " + paziente.getProvinciaNascita() + ", " + paziente.getNazionalita() + "\nIl paziente " + (paziente.isPericoloso() ? "" : "non ")
                + "è pericoloso\nIl paziente " + (paziente.isAutosufficiente() ? "" : "non ") + "è autosufficiente");
        mdp.addParagraphOfText("Contatti:\nNumero di telefono: " + paziente.getnTelefono());
        mdp.addParagraphOfText("Diagnosi:\n" + (paziente.getDiagnosi() == null ? "Il paziente non è stato ancora diagnosticato" : paziente.getDiagnosi()));
        for(Prescrizione prescrizione : prescrizioneRepository.findByPazienteOrderByDataPrescrizione(paziente)){
            mdp.addParagraphOfText("Prescrizione:\nPrescrizione effettuata in data: " + format.format(prescrizione.getDataPrescrizione()) + "\nNome commerciale farmaco: " + prescrizione.getFarmaco().getNomeCommerciale() +
                    "\nPrincipio attivo: " + prescrizione.getFarmaco().getPrincipioAttivo() + "\nDosaggio: " + prescrizione.getDosaggio() + "\nPrescritta dal Dr: " + prescrizione.getMedicoPrescrittore().getCognome() + " " + prescrizione.getMedicoPrescrittore().getNome());
        }
        for(Visita visita : visitaRepository.getVisitaByPazienteOrderByDataVisita(paziente)){
            if(visita.getDataVisita().before(Calendar.getInstance().getTime())){
                mdp.addParagraphOfText("Visita:\nVisita tenutasi il: " + new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(visita.getDataVisita()) +
                        "\nMedico: " + visita.getMedico().getCognome() + " " + visita.getMedico().getNome() + "\nOsservazioni: " + (visita.getOsservazioni() == null ? "Nessuna osservazione" : visita.getOsservazioni()));
            }
        }
        wordMLPackage.save(file);
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("medico", loggedMedico);
        return "homepage";
    }

    @RequestMapping("/agenda")
    public String agenda(Model model){
        List<Visita> visitaList = new LinkedList<>();
        for(Visita visita : visitaRepository.getVisitaByMedicoOrderByDataVisita(loggedMedico))
            visitaList.add(visita);
        model.addAttribute("visite", visitaList);
        return "agenda";
    }

    @RequestMapping("/lista-pazienti")
    public String listaPazienti(Model model){
        List<Paziente> listaPazienti = new LinkedList<>();
        for(Paziente paziente : pazienteRepository.findAll())
            listaPazienti.add(paziente);
        model.addAttribute("pazienti", listaPazienti);
        return "listaPazienti";
    }

    @RequestMapping("/cartella-clinica/{pazienteID}")
    public String visualizzaCartella(@PathVariable (value = "pazienteID") long idPaziente, Model model){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        if (paziente != null) {
            model.addAttribute("paziente", paziente);
            List<Visita> visitaList = new LinkedList<>();
            for(Visita visita : visitaRepository.getVisitaByPazienteOrderByDataVisita(paziente))
                visitaList.add(visita);
            model.addAttribute("visite", visitaList);
            List<Prescrizione> listaPrescrizione = new LinkedList<>();
            for(Prescrizione prescrizione : prescrizioneRepository.findByPazienteOrderByDataPrescrizione(paziente))
                listaPrescrizione.add(prescrizione);
            model.addAttribute("prescrizioni", listaPrescrizione);
            return "cartella";
        }
        return "redirect:/";
    }

    @RequestMapping("/modifica-cartella/{pazienteID}")
    public String modificaCartella(@PathVariable (value = "pazienteID") long idPaziente, Model model){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        if (paziente != null){
            model.addAttribute("paziente", paziente);
            return "modificaCartella";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/salva-cartella/{pazienteID}")
    public String salvaCartella(@PathVariable (value = "pazienteID") long idPaziente, @RequestParam(name = "cognome") String cognome, @RequestParam(name = "nome") String nome,
                                @RequestParam(name = "dataNascita") Date dataNascita, @RequestParam(name = "comuneNascita") String comuneNascita,
                                @RequestParam(name = "provinciaNascita") String provinciaNascita, @RequestParam(name = "nazionalita") String nazionalita,
                                @RequestParam(name = "diagnosi", required = false) String diagnosi, @RequestParam(name = "pericoloso", required = false) String pericoloso,
                                @RequestParam(name = "autosufficiente", required = false) String autosufficiente, @RequestParam(name = "nTelefono") String nTelefono){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        if(paziente == null)
            return "redirect:/";
        paziente.setCognome(cognome);
        paziente.setNome(nome);
        paziente.setDataDiNascita(dataNascita);
        paziente.setComuneNascita(comuneNascita);
        paziente.setProvinciaNascita(provinciaNascita);
        paziente.setNazionalita(nazionalita);
        if(diagnosi.equals("")){
            paziente.setDiagnosi(null);
            paziente.setDataDiagnosi(null);
        } else {
            paziente.setDiagnosi(diagnosi);
            paziente.setDataDiagnosi(Calendar.getInstance().getTime());
        }
        paziente.setPericoloso(pericoloso != null);
        paziente.setAutosufficiente(autosufficiente != null);
        paziente.setnTelefono(nTelefono);
        pazienteRepository.save(paziente);
        return "redirect:/cartella-clinica/" + paziente.getId();
    }

    @RequestMapping(value = "/nuova-prescrizione/{pazienteID}")
    public String nuovaPrescrizione(@PathVariable (value = "pazienteID") long idPaziente, Model model){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        if(paziente == null)
            return "redirect:/";
        List<Farmaco> farmaciList = new LinkedList<>();
        for(Farmaco farmaco : farmacoRepository.findAll()){
            farmaciList.add(farmaco);
        }
        model.addAttribute("farmaci", farmaciList);
        model.addAttribute("paziente", paziente);
        return "nuovaPrescrizione";
    }

    @RequestMapping(value = "/salva-prescrizione/{pazienteID}")
    public String salvaPrescrizione(@PathVariable (value = "pazienteID") long idPaziente, @RequestParam(name = "farmaco") long idFarmaco, @RequestParam(name = "dosaggio") String dosaggio){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        if(paziente == null)
            return "redirect:/";
        Farmaco farmaco = farmacoRepository.findById(idFarmaco);
        if(farmaco != null)
            prescrizioneRepository.save(new Prescrizione(farmaco, Calendar.getInstance().getTime(), dosaggio, loggedMedico, paziente));
        return "redirect:/cartella-clinica/" + paziente.getId();
    }

    @RequestMapping(value = "/visita/{visitaID}")
    public String visualizzaVisita(@PathVariable (value = "visitaID") long idVisita, Model model){
        Visita visita = visitaRepository.getVisitaByIdVisita(idVisita);
        if(visita == null)
            return "redirect:/";
        model.addAttribute("visita", visita);
        return "visita";
    }

    @RequestMapping(value = "/salva-osservazioni/{visitaID}")
    public String salvaOsservazioni(@PathVariable (value = "visitaID") long idVisita, @RequestParam(name = "osservazioni", required = false) String osservazioni){
        Visita visita = visitaRepository.getVisitaByIdVisita(idVisita);
        if(visita == null)
            return "redirect:/";
        if(osservazioni.equals(""))
            visita.setOsservazioni(null);
        else
            visita.setOsservazioni(osservazioni);
        visitaRepository.save(visita);
        return "redirect:/cartella-clinica/" + visita.getPaziente().getId();
    }

    @RequestMapping(value = "/scarica-report/{pazienteID}")
    public ResponseEntity<FileSystemResource> scaricaReport(@PathVariable(value = "pazienteID") long idPaziente){
        Paziente paziente = pazienteRepository.getPazienteById(idPaziente);
        try {
            File file = File.createTempFile("Report" + paziente.getCognome() + " " + paziente.getNome(), ".docx");
            creaReport(file, paziente);
            long fileLength = file.length(); // this is ok, but see note below

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.setContentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
            respHeaders.setContentLength(fileLength);
            respHeaders.setContentDispositionFormData("attachment", "Report " + paziente.getCognome() + " " + paziente.getNome() + ".docx");

            return new ResponseEntity<>(
                    new FileSystemResource(file), respHeaders, HttpStatus.OK
            );
        } catch (IOException | Docx4JException e) {
            return null;
        }
    }
}
