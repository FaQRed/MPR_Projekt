package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Projekt.exception.InvalidPudelDataException;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.repository.PudelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PudelService {
    private PudelRepository repository;
    private StringUtilService stringUtilService;

    @Autowired
    public PudelService(PudelRepository pudelRepository, StringUtilService stringUtilService) {
        this.repository = pudelRepository;
        this.stringUtilService = stringUtilService;
        Pudel pudel1 = new Pudel("LEO", 1, "TOY");
        Pudel pudel2 = new Pudel("MAO", 12, "ROYAL");
        Pudel pudel3 = new Pudel("PUO", 7, "CARLIC");

        this.repository.save(pudel1);
        this.repository.save(pudel2);
        this.repository.save(pudel3);


    }


    public List<Pudel> getPudelList() {
        List<Pudel> pudels = (List<Pudel>) this.repository.findAll();

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }


    public void createPudel(Pudel pudel) {
        validatePudel(pudel);
        stringUtilService.allFieldsToUpperCase(pudel);
        this.repository.save(pudel);
    }


    public Pudel getPudelById(Long id) {
        Optional<Pudel> pudel = this.repository.findById(id);
        if (pudel.isEmpty()) {
            throw new PudelNotFoundException();
        }

        return pudel.get();

    }

    public void deletePudelById(Long id) {
        if (this.repository.findById(id).isEmpty()) {
            throw new PudelNotFoundException();
        }
        repository.deleteById(id);
    }

    public void updatePudel(Long id, Pudel updatedPudel) {
        Optional<Pudel> pudel = repository.findById(id);
        if (pudel.isEmpty()) {
            throw new PudelNotFoundException();
        }
        validatePudel(updatedPudel);
        Pudel existingPudel = pudel.get();
        existingPudel.setName(updatedPudel.getName());
        existingPudel.setAge(updatedPudel.getAge());
        existingPudel.setClasification(updatedPudel.getClasification());
        existingPudel.setIdentificator(updatedPudel.countIdentificator());
        stringUtilService.allFieldsToUpperCase(existingPudel);
        repository.save(existingPudel);
    }

    public List<Pudel> getPudelByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidPudelDataException("Pudel name cannot be null or empty.");
        }

        List<Pudel> pudels = repository.findByName(name);

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }

    public List<Pudel> getPudelByAge(int age) {
        if (age <= 0) {
            throw new InvalidPudelDataException("Pudel age must be a positive number.");
        }

        List<Pudel> pudels = repository.findPudelByAge(age);

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }


    private void validatePudel(Pudel pudel) {
        if (pudel == null) {
            throw new InvalidPudelDataException("Pudel cannot be null.");
        }
        if (pudel.getName() == null || pudel.getName().isEmpty()) {
            throw new InvalidPudelDataException("Pudel name cannot be null or empty.");
        }
        if (pudel.getAge() <= 0) {
            throw new InvalidPudelDataException("Pudel age must be a positive number.");
        }
        if (pudel.getClasification() == null || pudel.getClasification().isEmpty()) {
            throw new InvalidPudelDataException("Pudel classification cannot be null or empty.");
        }
    }


}
