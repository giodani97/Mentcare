package univr.mentcare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import univr.mentcare.Models.Medico;
import univr.mentcare.Models.Paziente;
import univr.mentcare.Models.Visita;
import univr.mentcare.Repository.*;

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
        InitRepositories(MedicoRepository medicoRepository, PazienteRepository pazienteRepository, VisitaRepository visitaRepository) throws ParseException {
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



}
