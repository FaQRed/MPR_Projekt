package pl.edu.pjatk.MPR_Projekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.repository.PudelRepository;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;
import pl.edu.pjatk.MPR_Projekt.service.StringUtilService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PudelServiceTest {

    private PudelService service;

    private StringUtilService stringUtilService;
    protected PudelRepository pudelRepository;

    @BeforeEach
    public void setup(){
        this.stringUtilService = Mockito.mock(StringUtilService.class);
        this.pudelRepository = Mockito.mock(PudelRepository.class);
        this.service = new PudelService(pudelRepository,stringUtilService);

        clearInvocations(pudelRepository);
    }

    @Test
    public void createSetsPudelToUpperCase(){

        Pudel pudel = new Pudel("Leon", 12, "Toy");

        this.service.createPudel(pudel);

        verify(stringUtilService, times(1)).allFieldsToUpperCase(any());
        verify(pudelRepository, times(1)).save(any());


    }
}
