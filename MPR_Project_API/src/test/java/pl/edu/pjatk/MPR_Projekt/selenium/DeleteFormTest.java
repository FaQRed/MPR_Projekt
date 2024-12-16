package pl.edu.pjatk.MPR_Projekt.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteFormTest {
    WebDriver webDriver;

    @BeforeEach
    public void setup() {
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testDeletePudel() {
        ViewAllPage viewAllPage = new ViewAllPage(webDriver);
        viewAllPage.open();

        String idToDelete = "2";

        viewAllPage.deletePudelByDataId(idToDelete);
        assertThrows(NoSuchElementException.class, () -> viewAllPage.getRowById(idToDelete));
    }


}
