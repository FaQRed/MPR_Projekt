package pl.edu.pjatk.MPR_Projekt.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {

    WebDriver webDriver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "age")
    private WebElement ageInput;

    @FindBy(id = "classification")
    private WebElement classificationInput;

    @FindBy(id = "submit")
    private WebElement submitButton;


    public AddFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // <-- inicjalizuje adnotacje
    }

    public AddFormPage open() {
        this.webDriver.get("http://localhost:8080/addForm");

        return this;
    }

    public AddFormPage fillNameInput(String string) {
        this.nameInput.sendKeys(string);
        return this;
    }

    public AddFormPage fillAgeInput(String string) {
        this.ageInput.sendKeys(string);
        return this;
    }

    public AddFormPage fillClassificationInput(String string) {
        this.classificationInput.sendKeys(string);
        return this;
    }

    public ViewAllPage submitForm(){
        this.submitButton.submit();
        return new ViewAllPage(webDriver);

    }


}
