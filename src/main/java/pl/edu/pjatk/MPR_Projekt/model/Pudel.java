package pl.edu.pjatk.MPR_Projekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pudel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private String clasification;
    private Long Identificator;

    public long countIdentificator() {
        long suma = 0;
        for (char c : this.name.toCharArray()) {
            suma += (int) c;
        }
        suma += this.age;
        return suma;
    }


    public Pudel() {
    }

    public Pudel(String name, int age, String clasification) {

        this.name = name;
        this.age = age;
        this.clasification = clasification;
        this.setIdentificator(countIdentificator());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public Long getIdentificator() {
        return Identificator;
    }

    public void setIdentificator(Long identificator) {
        Identificator = identificator;
    }
}
