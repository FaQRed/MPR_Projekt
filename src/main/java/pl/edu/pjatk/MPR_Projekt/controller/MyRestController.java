package pl.edu.pjatk.MPR_Projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;

import java.util.List;
import java.util.Optional;


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
    public Optional<Pudel> getById(@PathVariable Long id) {
        return this.pudelService.getPudelById(id);
    }

    @PostMapping
    public void create(@RequestBody Pudel pudel) {
        this.pudelService.createPudel(pudel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.pudelService.deletePudelById(id);
    }

    @PutMapping("/id/{id}")
    public void update(@PathVariable Long id, @RequestBody Pudel pudel) {
        this.pudelService.updatePudel(id, pudel);
    }

    @GetMapping("/name/{name}")
    public List<Pudel> getByName(@PathVariable String name){
       return this.pudelService.getPudelByName(name);
    }
    @GetMapping("/age/{age}")
    public List<Pudel> getByAge(@PathVariable int age){
        return this.pudelService.getPudelByAge(age);
    }

}
