package pl.edu.pjatk.MPR_Projekt.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditFormTest {
    WebDriver webDriver;
    @BeforeEach
    public void setup() {
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testEditPudel() {
        ViewAllPage viewAllPage = new ViewAllPage(webDriver);
        viewAllPage.open();

        String idToEdit = "2";

        viewAllPage.clickEditButtonById(idToEdit);

        EditFormPage editFormPage = new EditFormPage(webDriver);
        editFormPage
                .fillNameInput("Updatedname")
                .fillAgeInput("8")
                .fillClassificationInput("Miniature")
                .submitForm();

        ViewAllPage updatedViewAllPage = new ViewAllPage(webDriver);
        assertEquals("Updatedname", updatedViewAllPage.getNameByDataId(idToEdit));
    }
}
