package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        List<Pudel> pudels =  (List<Pudel>) this.repository.findAll();

        for(Pudel pudel: pudels){
            stringUtilService.allFieldsFromUpLetter(pudel);
        }

        return pudels;


    }


    public void createPudel(Pudel pudel) {
       stringUtilService.allFieldsToUpperCase(pudel);
       this.repository.save(pudel);
    }


    public Optional<Pudel> getPudelById(Long id) {
            Optional<Pudel> pudel =  this.repository.findById(id);
        pudel.ifPresent(value -> stringUtilService.allFieldsFromUpLetter(value));
            return pudel;

    }

    public void deletePudelById(Long id) {

            repository.deleteById(id);
    }

    public void updatePudel(Long id, Pudel updatedPudel) {

            Optional<Pudel> pudel = repository.findById(id);
            if (pudel.isPresent()) {

                pudel.get().setName(updatedPudel.getName());
                pudel.get().setAge(updatedPudel.getAge());
                pudel.get().setClasification(updatedPudel.getClasification());
                pudel.get().setIdentificator(updatedPudel.countIdentificator());
                stringUtilService.allFieldsToUpperCase(pudel.get());
            }
    }

    public List<Pudel> getPudelByName(String name) {
        List<Pudel> pudels = repository.findByName(name);
        for (Pudel pudel: pudels){
            stringUtilService.allFieldsFromUpLetter(pudel);
        }
        return pudels;
    }

    public List<Pudel> getPudelByAge(int age){
        List<Pudel> pudels = repository.findPudelByAge(age);
        for (Pudel pudel: pudels){
            stringUtilService.allFieldsFromUpLetter(pudel);
        }
        return pudels;
    }


}
