package pl.edu.pjatk.MPR_Projekt.services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;

import java.util.ArrayList;
import java.util.List;

@Component
public class PudelService {

    List<Pudel> pudelList = new ArrayList<>();

    public PudelService(){
        pudelList.add(new Pudel("Leo", 1, "Toy"));
        pudelList.add(new Pudel("Mao", 12, "Royal"));
        pudelList.add(new Pudel("Puo", 7, "Carlic"));
    }

    public List<Pudel> getPudelList() {
        return this.pudelList;
    }

    public void createPudel(Pudel pudel){
        this.pudelList.add(pudel);
    }


    public Pudel getPudelLById(int id) {
        if (id >= 0 && id < pudelList.size()) {
            return pudelList.get(id);
        } else {
            throw new IndexOutOfBoundsException("No Pudel found with the given ID.");
        }
    }

    public void deletePudelById(int id){
        if(id >=0 && id < pudelList.size()){
            pudelList.remove(id);
        } else {
            throw new IndexOutOfBoundsException("No Pudel found with the given ID.");
        }
    }

    public void updatePudel(int id, Pudel updatedPudel) {
        if (id >= 0 && id < pudelList.size()) {
            Pudel pudel = pudelList.get(id);
            pudel.setName(updatedPudel.getName());
            pudel.setAge(updatedPudel.getAge());
            pudel.setClasification(updatedPudel.getClasification());
        } else {
            throw new IndexOutOfBoundsException("No Pudel found with the given ID.");
        }
    }
}
