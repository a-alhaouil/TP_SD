package org.example.unsecuredhospital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.unsecuredhospital.entities.Patient;
import org.example.unsecuredhospital.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    /*
     * Syntaxe pour récupérer un paramètre avec Spring MVC :
     * - direct  :
     *   index(int page)
     * - Utilisation de @RequestParam avec une valeur par défaut :
     *   index(@RequestParam(name = "page", defaultValue = "0") int page)
     *
     * Syntaxe pour récupérer un paramètre avec HttpServletRequest :
     *   index(HttpServletRequest request) {
     *       int page = Integer.parseInt(request.getParameter("page"));
     *   }
     */
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "5") int s,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
                        ){

        Page<Patient> pagePatients = patientRepository.findByNomContains(kw,PageRequest.of(p,s));
        model.addAttribute("patients", pagePatients.getContent());
        System.out.println(pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        System.out.println(pagePatients.getTotalPages());
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id,String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("")
    public String home(){
        return "redirect:/user/index";
    }
    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page){

        if(bindingResult.hasErrors()) return"formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(@RequestParam(name = "id") Long id, Model model,String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatient";
    }


}

