package univr.mentcare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import univr.mentcare.Models.*;
import univr.mentcare.Repository.*;


import javax.annotation.Resource;
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
    public ResponseEntity<Resource> scaricaReport(){
        return null;
    }
}
