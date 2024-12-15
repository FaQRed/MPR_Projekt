package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;


@Service
public class StringUtilService {
    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public void allFieldsToUpperCase(Pudel pudel) {
        pudel.setName(pudel.getName().toUpperCase());
        pudel.setClasification(pudel.getClasification().toUpperCase());
    }

    public void allFieldsFromUpLetter(Pudel pudel) {
        pudel.setName(capitalizeFirstLetter(pudel.getName()));
        pudel.setClasification(capitalizeFirstLetter(pudel.getClasification()));
    }
}
