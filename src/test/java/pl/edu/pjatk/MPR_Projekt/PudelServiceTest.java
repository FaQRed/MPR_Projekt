package pl.edu.pjatk.MPR_Projekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.repository.PudelRepository;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;
import pl.edu.pjatk.MPR_Projekt.service.StringUtilService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PudelServiceTest {

    private PudelService service;
    private StringUtilService stringUtilService;
    private PudelRepository pudelRepository;

    @BeforeEach
    public void setup() {
        this.stringUtilService = Mockito.mock(StringUtilService.class);
        this.pudelRepository = Mockito.mock(PudelRepository.class);
        this.service = new PudelService(pudelRepository, stringUtilService);

        clearInvocations(pudelRepository);
    }

    @Test
    public void createPudel_shouldConvertFieldsToUpperCaseAndSave() {
        Pudel pudel = new Pudel("Leon", 12, "Toy");
        service.createPudel(pudel);
        verify(stringUtilService, times(1)).allFieldsToUpperCase(pudel);
        verify(pudelRepository, times(1)).save(pudel);
    }

    @Test
    public void getPudelList_shouldReturnAllPudelsWithUpperCaseFields() {
        List<Pudel> pudels = Arrays.asList(
                new Pudel("Leo", 1, "Toy"),
                new Pudel("Mao", 12, "Royal")
        );
        when(pudelRepository.findAll()).thenReturn(pudels);

        List<Pudel> result = service.getPudelList();

        verify(stringUtilService, times(1)).allFieldsFromUpLetter(pudels.get(0));
        verify(stringUtilService, times(1)).allFieldsFromUpLetter(pudels.get(1));
        assertEquals(pudels, result);
    }

    @Test
    public void getPudelById_shouldReturnPudelIfExists() {
        Pudel pudel = new Pudel("Leo", 1, "Toy");
        when(pudelRepository.findById(1L)).thenReturn(Optional.of(pudel));

        Optional<Pudel> result = service.getPudelById(1L);

        verify(stringUtilService, times(1)).allFieldsFromUpLetter(pudel);
        assertTrue(result.isPresent());
        assertEquals(pudel, result.get());
    }

    @Test
    public void getPudelById_shouldReturnEmptyOptionalIfNotExists() {
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pudel> result = service.getPudelById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    public void deletePudelById_shouldDeletePudel() {
        service.deletePudelById(1L);
        verify(pudelRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updatePudel_shouldUpdateExistingPudelAndConvertFieldsToUpperCase() {
        Pudel existingPudel = new Pudel("Leo", 1, "Toy");
        Pudel updatedPudel = new Pudel("Milo", 2, "Royal");
        when(pudelRepository.findById(1L)).thenReturn(Optional.of(existingPudel));

        service.updatePudel(1L, updatedPudel);

        assertEquals("Milo", existingPudel.getName());
        assertEquals(2, existingPudel.getAge());
        assertEquals("Royal", existingPudel.getClasification());
        verify(stringUtilService, times(1)).allFieldsToUpperCase(existingPudel);
    }

    @Test
    public void updatePudel_shouldNotUpdateIfPudelDoesNotExist() {
        Pudel updatedPudel = new Pudel("Milo", 2, "Royal");
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());

        service.updatePudel(1L, updatedPudel);

        verify(pudelRepository, never()).save(any());
        verify(stringUtilService, never()).allFieldsToUpperCase(any());
    }

    @Test
    public void getPudelByName_shouldReturnMatchingPudelsWithUpperCaseFields() {
        List<Pudel> pudels = List.of
                (new Pudel("Leo", 1, "Toy"));
        when(pudelRepository.findByName("Leo")).thenReturn(pudels);

        List<Pudel> result = service.getPudelByName("Leo");

        verify(stringUtilService, times(1)).allFieldsFromUpLetter(pudels.getFirst());
        assertEquals(pudels, result);
    }

    @Test
    public void getPudelByAge_shouldReturnMatchingPudelsWithUpperCaseFields() {
        List<Pudel> pudels = List.of(new Pudel("Leo", 1, "Toy"));
        when(pudelRepository.findPudelByAge(1)).thenReturn(pudels);

        List<Pudel> result = service.getPudelByAge(1);

        verify(stringUtilService, times(1)).allFieldsFromUpLetter(pudels.getFirst());
        assertEquals(pudels, result);
    }
}
