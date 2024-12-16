package pl.edu.pjatk.MPR_Projekt.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pl.edu.pjatk.MPR_Projekt.exception.InvalidPudelDataException;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class PudelService {
    private RestClient restClient;
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
        List<Pudel> pudels = (List<Pudel>) this.repository.findAll();

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }


    public void createPudel(Pudel pudel) {
        validatePudel(pudel);
        stringUtilService.allFieldsToUpperCase(pudel);
        this.repository.save(pudel);
    }


    public Pudel getPudelById(Long id) {
        Optional<Pudel> pudel = this.repository.findById(id);
        if (pudel.isEmpty()) {
            throw new PudelNotFoundException();
        }

        return pudel.get();

    }

    public void deletePudelById(Long id) {
        if (this.repository.findById(id).isEmpty()) {
            throw new PudelNotFoundException();
        }
        repository.deleteById(id);
    }

    public void updatePudel(Long id, Pudel updatedPudel) {
        Optional<Pudel> pudel = repository.findById(id);
        if (pudel.isEmpty()) {
            throw new PudelNotFoundException();
        }
        validatePudel(updatedPudel);
        Pudel existingPudel = pudel.get();
        existingPudel.setName(updatedPudel.getName());
        existingPudel.setAge(updatedPudel.getAge());
        existingPudel.setClasification(updatedPudel.getClasification());
        existingPudel.setIdentificator(updatedPudel.countIdentificator());
        stringUtilService.allFieldsToUpperCase(existingPudel);
        repository.save(existingPudel);
    }

    public List<Pudel> getPudelByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidPudelDataException("Pudel name cannot be null or empty.");
        }

        List<Pudel> pudels = repository.findByName(name);

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }

    public List<Pudel> getPudelByAge(int age) {
        if (age <= 0) {
            throw new InvalidPudelDataException("Pudel age must be a positive number.");
        }

        List<Pudel> pudels = repository.findPudelByAge(age);

        if (pudels.isEmpty()) {
            throw new PudelNotFoundException();
        }

        pudels.forEach(stringUtilService::allFieldsFromUpLetter);
        return pudels;
    }


    private void validatePudel(Pudel pudel) {
        if (pudel == null) {
            throw new InvalidPudelDataException("Pudel cannot be null.");
        }
        if (pudel.getName() == null || pudel.getName().isEmpty()) {
            throw new InvalidPudelDataException("Pudel name cannot be null or empty.");
        }
        if (pudel.getAge() <= 0) {
            throw new InvalidPudelDataException("Pudel age must be a positive number.");
        }
        if (pudel.getClasification() == null || pudel.getClasification().isEmpty()) {
            throw new InvalidPudelDataException("Pudel classification cannot be null or empty.");
        }
    }





    public byte[] generatePudelPdf(Long id) {
        Optional<Pudel> pudelOpt = repository.findById(id);
        if (pudelOpt.isEmpty()) {
            throw new PudelNotFoundException();
        }

        Pudel pudel = pudelOpt.get();
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Pudel Information");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                contentStream.showText("ID: " + pudel.getId());
                contentStream.newLine();
                contentStream.showText("Name: " + pudel.getName());
                contentStream.newLine();
                contentStream.showText("Age: " + pudel.getAge());
                contentStream.newLine();
                contentStream.showText("Classification: " + pudel.getClasification());
                contentStream.newLine();
                contentStream.showText("Identifier: " + pudel.getIdentificator());

                contentStream.endText();
            }

            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }




}
