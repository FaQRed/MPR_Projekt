package pl.edu.pjatk.MPR_Projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.services.PudelService;

import java.util.List;


@RestController
public class MyRestController {
    private PudelService pudelSrvice;

    @Autowired
    public MyRestController(PudelService pudelSrvice) {
        this.pudelSrvice = pudelSrvice;
    }

    @GetMapping("pudel/all")
    public List<Pudel> getAll() {
        return this.pudelSrvice.getPudelList();
    }

    @PostMapping("pudel")
    public void create(@RequestBody Pudel pudel) {
        this.pudelSrvice.createPudel(pudel);
    }

    @DeleteMapping("pudel/{id}")
    public void delete(@PathVariable int id) {
        this.pudelSrvice.deletePudelById(id);
    }

    @GetMapping("pudel/{}id")
    public Pudel getById(@PathVariable int id) {
        return this.pudelSrvice.getPudelLById(id);
    }

}
