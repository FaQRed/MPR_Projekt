package pl.edu.pjatk.MPR_Projekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Projekt.exception.InvalidPudelDataException;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.repository.PudelRepository;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;
import pl.edu.pjatk.MPR_Projekt.service.StringUtilService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void deletePudelById() {
        when(pudelRepository.findById(1L)).thenReturn( Optional.of(new Pudel()));
        service.deletePudelById(1L);
        verify(pudelRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deletePudelById_throwsEmptyException() {
        when(pudelRepository.findById(1L)).thenReturn( Optional.empty());

        assertThrows(PudelNotFoundException.class, ()-> {
            service.deletePudelById(1L);
        });

        verify(pudelRepository, times(0)).deleteById(1L);
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
    public void updatePudel_shouldThrowExceptionIfPudelDoesNotExist() {
        Pudel updatedPudel = new Pudel("Milo", 2, "Royal");
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PudelNotFoundException.class, () -> service.updatePudel(1L, updatedPudel));

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


    @Test
    void shouldThrowExceptionWhenPudelListIsEmpty() {
        when(pudelRepository.findAll()).thenReturn(List.of());

        assertThrows(PudelNotFoundException.class, () -> service.getPudelList());
    }




    @Test
    void shouldThrowExceptionWhenPudelByIdNotFound() {
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PudelNotFoundException.class, () -> service.getPudelById(1L));
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentPudel() {
        when(pudelRepository.existsById(1L)).thenReturn(false);

        assertThrows(PudelNotFoundException.class, () -> service.deletePudelById(1L));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentPudel() {
        Pudel updatedPudel = new Pudel("MAO", 2, "ROYAL");
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PudelNotFoundException.class, () -> service.updatePudel(1L, updatedPudel));
    }

    @Test
    void shouldThrowExceptionWhenPudelByNameNotFound() {
        when(pudelRepository.findByName("UNKNOWN")).thenReturn(List.of());

        assertThrows(PudelNotFoundException.class, () -> service.getPudelByName("UNKNOWN"));
    }

    @Test
    void shouldThrowExceptionWhenPudelByAgeNotFound() {
        when(pudelRepository.findPudelByAge(99)).thenReturn(List.of());

        assertThrows(PudelNotFoundException.class, () -> service.getPudelByAge(99));
    }


}
