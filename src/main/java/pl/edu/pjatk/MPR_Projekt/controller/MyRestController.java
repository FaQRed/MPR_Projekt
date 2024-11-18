package pl.edu.pjatk.MPR_Projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;

import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<List<Pudel>> getAll() {
        return new ResponseEntity<>(this.pudelService.getPudelList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pudel> getById(@PathVariable Long id) {

        return new ResponseEntity<>(this.pudelService.getPudelById(id), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Pudel pudel) {
        this.pudelService.createPudel(pudel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.pudelService.deletePudelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Pudel pudel) {
        this.pudelService.updatePudel(id, pudel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pudel>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(this.pudelService.getPudelByName(name), HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Pudel>> getByAge(@PathVariable int age) {
        return new ResponseEntity<>(this.pudelService.getPudelByAge(age), HttpStatus.OK);
    }

}
