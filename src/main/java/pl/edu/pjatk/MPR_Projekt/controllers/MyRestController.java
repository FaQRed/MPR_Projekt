package pl.edu.pjatk.MPR_Projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.services.PudelService;

import java.util.List;


@RestController
@RequestMapping("/pudel")
public class MyRestController {
    private PudelService pudelService;

    @Autowired
    public MyRestController(PudelService pudelService) {
        this.pudelService = pudelService;
    }

    @GetMapping("/all")
    public List<Pudel> getAll() {
        return this.pudelService.getPudelList();
    }
    @GetMapping("/{id}")
    public Pudel getById(@PathVariable int id) {
        return this.pudelService.getPudelLById(id);
    }

    @PostMapping
    public void create(@RequestBody Pudel pudel) {
        this.pudelService.createPudel(pudel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.pudelService.deletePudelById(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody Pudel pudel) {
        this.pudelService.updatePudel(id, pudel);

    }}
