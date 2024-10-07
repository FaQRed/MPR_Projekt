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

    public void deletePudelById(int id) {
        this.pudelList.remove(id);
    }

    public Pudel getPudelLById(int id) {
        return this.pudelList.get(id);
    }
}
