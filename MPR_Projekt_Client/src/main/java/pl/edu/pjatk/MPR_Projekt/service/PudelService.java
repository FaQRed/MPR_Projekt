package pl.edu.pjatk.MPR_Projekt.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pl.edu.pjatk.MPR_Projekt.exception.InvalidPudelDataException;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;


import java.util.List;


@Component
public class PudelService {
    private RestClient restClient;
    private final String apiUrl = "http://localhost:8081/pudel";


    public PudelService() {
        this.restClient = RestClient.create();
    }


    public List<Pudel> getPudelList() {
        List<Pudel> pudels = restClient.get()
                .uri(apiUrl + "/all")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Pudel>>() {
                });

        assert pudels != null;
        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        return pudels;
    }


    public void createPudel(Pudel pudel) {
        restClient.post()
                .uri(apiUrl)
                .body(pudel)
                .retrieve()
                .toBodilessEntity();
    }


    public Pudel getPudelById(Long id) {
        Pudel pudel = restClient.get()
                .uri(apiUrl + "/" + id)
                .retrieve()
                .body(Pudel.class);


        if (pudel == null) {
            throw new PudelNotFoundException();
        }

        return pudel;

    }

    public void deletePudelById(Long id) {
        restClient.delete()
                .uri(apiUrl + "/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    public void updatePudel(Long id, Pudel updatedPudel) {
        restClient.put()
                .uri(apiUrl + "/id/" + id)
                .body(updatedPudel)
                .retrieve()
                .toBodilessEntity();
    }

    public List<Pudel> getPudelByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidPudelDataException("Pudel name cannot be null or empty.");
        }

        List<Pudel> pudels = restClient.get()
                .uri(apiUrl + "name/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Pudel>>() {
                });

        assert pudels != null;
        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        return pudels;
    }

    public List<Pudel> getPudelByAge(int age) {
        if (age <= 0) {
            throw new InvalidPudelDataException("Pudel age must be a positive number.");
        }

        List<Pudel> pudels = restClient.get()
                .uri(apiUrl + "age/" + age)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Pudel>>() {
                });

        assert pudels != null;
        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        return pudels;
    }






}
