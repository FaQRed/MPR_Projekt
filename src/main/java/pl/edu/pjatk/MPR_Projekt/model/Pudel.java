package pl.edu.pjatk.MPR_Projekt.model;

public class Pudel extends Dog{
    private String clasification;

    public Pudel(String name, int age, String classification) {
        super(name, age);
        this.clasification = classification;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }
}
