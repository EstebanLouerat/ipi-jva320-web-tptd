package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.io.Console;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class SalariesController {

    @Autowired
    private SalarieAideADomicileService salarieService;

    @GetMapping("/salaries/{id}")
    public String detailSalarie(@PathVariable("id") Long id, ModelMap model) throws SalarieException {
        SalarieAideADomicile salarie = salarieService.getSalarie(id);
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeannette Dupontelle",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 10,
                1, 0);

//        salarieService.creerSalarieAideADomicile(aide);

        model.put("salarie", salarie);
        model.addAttribute("salarieCount", salarieService.countSalaries());
        return "detail_Salarie";
    }

    @GetMapping("/salaries/aide/new")
    public String creerSalarie(ModelMap model) {
        SalarieAideADomicile newSalarie = new SalarieAideADomicile();

        newSalarie.setId(salarieService.countSalaries() + 1);
        model.put("salarie", newSalarie);
        model.addAttribute("salarieCount", salarieService.countSalaries());
        return "detail_Salarie";
    }

    @GetMapping("/salaries")
    public String listSalarie(
            @RequestParam(required = false, name = "nom") String nom,
            @RequestParam(required = false, name = "page", defaultValue = "1") int page,
            @RequestParam(required = false, name = "taille", defaultValue = "10") int taille,
            @RequestParam(required = false, name = "sortDirection", defaultValue = "desc") String sortDirection,
            @RequestParam(required = false, name = "sortProperty", defaultValue = "id") String sortProperty,
            final ModelMap model) {
        List<SalarieAideADomicile> salaries = salarieService.getSalaries();

        model.put("salaries", salaries);
        model.put("page", page);
        model.put("sortDirection", sortDirection);
        model.put("sortProperty", sortProperty);
        model.addAttribute("salarieCount", salarieService.countSalaries());
        return "list";
    }

    @PostMapping("/salaries/save")
    private String saveSalaries(@ModelAttribute SalarieAideADomicile salarie) throws SalarieException {
        salarieService.creerSalarieAideADomicile(salarie);

        return "redirect:/salaries";
    }

    @PostMapping("/salaries/update")
    private String updateSalaries(@ModelAttribute SalarieAideADomicile salarie) throws SalarieException {
        salarieService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries";
    }

    @GetMapping("/salaries?nom={nom}")
    public String getListByNom(@PathVariable("nom") String nom, ModelMap model) {
        List<SalarieAideADomicile> salaries = salarieService.getSalaries(nom);
        if (salaries.isEmpty())
        {
            return "not_found";
        }
//        model.put("salaries", salaries);
        model.addAttribute("errorMessage", "No User find with this name");
        return "home";
    }

    @GetMapping("/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable Long id) throws SalarieException {
        salarieService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }
}
