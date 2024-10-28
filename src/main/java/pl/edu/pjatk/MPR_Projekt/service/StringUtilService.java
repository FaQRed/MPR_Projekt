package pl.edu.pjatk.MPR_Projekt.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;

import java.lang.reflect.Field;

@Service
public class StringUtilService {
    public void allFieldsToUpperCase(Pudel pudel) {
        pudel.setName(pudel.getName().toUpperCase());
        pudel.setClasification(pudel.getClasification().toUpperCase());


    }




    public void allFieldsFromUpLetter(Pudel pudel){
        String first_part = pudel.getName().substring(0, 1).toUpperCase();
        int length = pudel.getName().length();
        String second_part = pudel.getName().substring(1,length).toLowerCase();
        pudel.setName(first_part + second_part);


        String first_part2 = pudel.getClasification().substring(0, 1).toUpperCase();
        int length2 = pudel.getClasification().length();
        String second_part2 = pudel.getClasification().substring(1,length).toLowerCase();
        pudel.setClasification(first_part + second_part);
    }
}
