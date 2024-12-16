package pl.edu.pjatk.MPR_Projekt;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Projekt.exception.PudelNotFoundException;
import pl.edu.pjatk.MPR_Projekt.model.Pudel;
import pl.edu.pjatk.MPR_Projekt.service.PudelService;
import pl.edu.pjatk.MPR_Projekt.service.StringUtilService;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PudelServicePdfTest {

    private PudelService pudelService;
    private PudelRepository pudelRepository;

    @BeforeEach
    public void setUp() {
        pudelRepository = Mockito.mock(PudelRepository.class);
        StringUtilService stringUtilService = new StringUtilService();
        pudelService = new PudelService(pudelRepository, stringUtilService);
    }

    @Test
    public void testGeneratePudelPdf_Success() throws Exception {
        // Arrange
        Pudel testPudel = new Pudel("Leo", 3, "Toy");
        testPudel.setId(1L);
        when(pudelRepository.findById(1L)).thenReturn(Optional.of(testPudel));

        byte[] pdfBytes = pudelService.generatePudelPdf(1L);


        assertNotNull(pdfBytes, "Generated PDF byte array should not be null");


        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            assertEquals(1, document.getNumberOfPages(), "PDF should contain exactly one page");
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfContent = pdfStripper.getText(document);

            assertTrue(pdfContent.contains("Pudel Information"), "PDF should contain the title");
            assertTrue(pdfContent.contains("ID: 1"), "PDF should contain the correct ID");
            assertTrue(pdfContent.contains("Name: Leo"), "PDF should contain the correct Name");
            assertTrue(pdfContent.contains("Age: 3"), "PDF should contain the correct Age");
            assertTrue(pdfContent.contains("Classification: Toy"), "PDF should contain the correct Classification");
        }
    }

    @Test
    public void testGeneratePudelPdf_PudelNotFound() {
        // Arrange
        when(pudelRepository.findById(1L)).thenReturn(Optional.empty());


        PudelNotFoundException exception = assertThrows(PudelNotFoundException.class, () -> pudelService.generatePudelPdf(1L));
        assertEquals("Pudel not found", exception.getMessage());
    }


}
