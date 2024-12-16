package pl.edu.pjatk.MPR_Projekt.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditFormPage {

    WebDriver webDriver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "age")
    private WebElement ageInput;

    @FindBy(id = "classification")
    private WebElement classificationInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public EditFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public EditFormPage fillNameInput(String name) {
        this.nameInput.clear();
        this.nameInput.sendKeys(name);
        return this;
    }

    public EditFormPage fillAgeInput(String age) {
        this.ageInput.clear();
        this.ageInput.sendKeys(age);
        return this;
    }

    public EditFormPage fillClassificationInput(String classification) {
        this.classificationInput.clear();
        this.classificationInput.sendKeys(classification);
        return this;
    }

    public ViewAllPage submitForm() {
        this.submitButton.submit();
        return new ViewAllPage(webDriver);
    }
}
