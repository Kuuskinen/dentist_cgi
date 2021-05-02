package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
@EnableJpaRepositories({"com.cgi.dentistapp.service"})
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Autowired
    private DentistService dentistService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/registrations").setViewName("registrations");
        registry.addViewController("/change_registration").setViewName("change_registration");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("dentists", dentistService.getAllDentists());
        return "form";
    }

    //NEEDS TO BE ADDED: https://www.baeldung.com/spring-boot-custom-error-page

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistId(), dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    //REQUESTS ALL REGISTRATIONS
    @GetMapping("/registrations")
    public String showRegistrations(Model model) {
        model.addAttribute("visits", dentistVisitService.getAllVisits());
        return "registrations";
    }

    @RequestMapping(value = "/delete_id", method = RequestMethod.POST)
    public String deleteVisit(@RequestBody String id) {
        dentistVisitService.deleteVisit(id);
        return "registrations";
    }

    @GetMapping("/change_registration/{id}")
    public String changeVisit(@PathVariable String id, Model model, DentistVisitDTO dentistVisitDTO) {
        DentistVisitEntity dentistVisitEntity = dentistVisitService.visitToChange(id);
        DentistVisitDTO dto = mapVisitEntityToVisitDTO(dentistVisitEntity);

        model.addAttribute("dentistVisitDTO", dto);
        model.addAttribute("dentists", dentistService.getAllDentists());
        model.addAttribute("entity_id", id);

        return "change_registration";
    }

    private DentistVisitDTO mapVisitEntityToVisitDTO(DentistVisitEntity entity) {
        DentistVisitDTO dto = new DentistVisitDTO();
        dto.setDentistId(entity.getDentistId());
        dto.setVisitTime(entity.getDate());

        String visitIdAsString = String.valueOf(entity.getId());
        dto.setVisitId(visitIdAsString);

        return dto;
    }

    @PostMapping("/change")
    private String postChangeForm(DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "change_registration";
        }

        long idAsLong = Long.parseLong(dentistVisitDTO.getVisitId());
        dentistVisitService.changeVisit(idAsLong, dentistVisitDTO);

        return "redirect:/registrations";
    }
}
