package com.ipi.jva320.controller;

import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SalarieAideADomicileService salarieService;

    @GetMapping("/")
    public String home(final ModelMap model) {
        Long nbSalaries = salarieService.countSalaries();
        model.put("salarie", nbSalaries);
        model.addAttribute("salarieCount", salarieService.countSalaries());
        return "home";
    }
}
