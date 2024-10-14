package pl.edu.pjatk.MPR_Projekt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;

import java.util.List;

@Repository
public interface PudelRepository extends CrudRepository<Pudel, Long> {

    public List<Pudel> findByName(String name);

    public List<Pudel> findPudelByAge(int age);

}
