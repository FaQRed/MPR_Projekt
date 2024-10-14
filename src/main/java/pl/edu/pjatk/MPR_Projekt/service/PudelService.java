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

    @Autowired
    public PudelService(PudelRepository pudelRepository) {
        this.repository = pudelRepository;
        this.repository.save(new Pudel("Leo", 1, "Toy"));
        this.repository.save(new Pudel("Mao", 12, "Royal"));
        this.repository.save(new Pudel("Puo", 7, "Carlic"));

    }


    public List<Pudel> getPudelList() {
        return (List<Pudel>) this.repository.findAll();
    }


    public void createPudel(Pudel pudel) {
        this.repository.save(pudel);
    }


    public Optional<Pudel> getPudelById(Long id) {
            return repository.findById(id);

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
                pudel.get().setIdentificator(pudel.get().countIdentificator());
            }
    }

    public List<Pudel> getPudelByName(String name) {
        return repository.findByName(name);
    }

    public List<Pudel> getPudelByAge(int age){
        return repository.findPudelByAge(age);
    }


}
