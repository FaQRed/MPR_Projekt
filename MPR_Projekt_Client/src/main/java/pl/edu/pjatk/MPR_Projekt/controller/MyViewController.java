package pl.edu.pjatk.MPR_Projekt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;

import java.util.List;

@Controller

public class MyViewController {
    private PudelService pudelService;

    public MyViewController(PudelService pudelService) {
        this.pudelService = pudelService;
    }

    @GetMapping("/view/all")
    public String displayAllPudels(Model model){
        model.addAttribute("nazwazmiennej", "jakaswartosc");
        List<Pudel> pudelList = this.pudelService.getPudelList();
        model.addAttribute(pudelList);
        return  "viewAll";
    }

    @GetMapping("/addForm")
    public String displayAddForm(Model model){
        model.addAttribute("pudel", new Pudel());
        return  "addForm";
    }

    @PostMapping("/addForm")
    public String submitForm(@ModelAttribute Pudel pudel){
        this.pudelService.createPudel(pudel);
        return  "redirect:/view/all";
    }


    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
            pudelService.deletePudelById(id);
            return "redirect:/view/all";
    }

    @GetMapping("/edit")
    public String editPudelForm(@RequestParam("id") Long id, Model model) {
        Pudel pudel = pudelService.getPudelById(id);
        model.addAttribute("pudel", pudel);
        return "editPudel";
    }

    @PostMapping("/edit")
    public String updatePudel(@ModelAttribute Pudel pudel) {
        pudelService.updatePudel(pudel.getId(), pudel);
        return "redirect:/view/all";
    }




}
