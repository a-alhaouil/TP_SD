package org.example.hopital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    private String index(Model model,
                         @RequestParam(name="page",defaultValue = "0") int p,
                         @RequestParam(name="size",defaultValue = "4")int s,
                         @RequestParam(name="keyword",defaultValue = "") String kw
                         ){
//        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(p,s));
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(p,s));
        model.addAttribute("listPatients",pagePatients);
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);

        return "patients";
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());

    return "formPatients";
    }
    @GetMapping("/editPatient")
    public String editPatients(Model model,Long id, String keyword, int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null)throw new RuntimeException("Patient not found");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);

        return "editPatient";
    }


    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
    patientRepository.deleteById(id);
    return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @PostMapping("/save")
    public String save(Model model,@Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()){return "formPatients";}
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
}
