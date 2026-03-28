package br.com.faculdadeinovatech.inovatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/inovatech")
public class InovatechController {

    @GetMapping("")
    public String index(Model model) {
        return "index"; 
    }
    
}
