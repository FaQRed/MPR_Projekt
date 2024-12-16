package pl.edu.pjatk.MPR_Projekt.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFormTest {
    WebDriver webDriver;

    @BeforeEach
    public void setup(){
//        this.webDriver = new FirefoxDriver();
        this.webDriver = new ChromeDriver();
//        this.webDriver = new EdgeDriver();
//        this.webDriver = new SafariDriver();
    }


    @Test
    public void testAddForm(){
        AddFormPage addFormPage = new AddFormPage(webDriver);
        addFormPage
                .open()
                .fillNameInput("Leuszzhka")
                .fillAgeInput("15")
                .fillClassificationInput("Toy");

                ViewAllPage viewAllPage = addFormPage.submitForm();

                assertEquals("Leuszzhka", viewAllPage.getNameFromLastRowOfTable());
                assertEquals("15", viewAllPage.getAgeFromLastRowOfTable());
                assertEquals("Toy", viewAllPage.getClassificationFromLastRowOfTable());
    }

}
