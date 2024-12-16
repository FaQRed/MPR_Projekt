package pl.edu.pjatk.MPR_Projekt.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class ViewAllPage {

    private WebDriver webDriver;

    @FindBy(tagName = "h1")
    private WebElement header;



    public ViewAllPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // <-- inicjalizuje adnotacje
    }

    public ViewAllPage open() {
        this.webDriver.get("http://localhost:8080/view/all");

        return this;
    }

    private WebElement getTable() {
        return this.webDriver.findElement(By.id("pudelTable"));
    }


    List<WebElement> getTableRows() {
        WebElement table = getTable();
        return table.findElements(By.tagName("tr")).subList(1, table.findElements(By.tagName("tr")).size());
    }

    private WebElement getLastTableRow() {
        List<WebElement> rows = getTableRows();
        return rows.getLast();
    }

    private List<WebElement> getLastRowCells() {
        WebElement lastRow = getLastTableRow();
        return lastRow.findElements(By.tagName("td"));
    }

    public String getNameFromLastRowOfTable() {
        return getLastRowCells().getFirst().getText();
    }

    public String getAgeFromLastRowOfTable() {
        return getLastRowCells().get(1).getText();
    }

    public String getClassificationFromLastRowOfTable() {
        return getLastRowCells().get(2).getText();
    }

    public void deletePudelByDataId(String id) {
        WebElement row = getRowById(id);

        // Находим кнопку "Delete" в этой строке
        WebElement deleteButton = row.findElement(By.xpath(".//a[@id='deleteButton']"));

        // Кликаем по кнопке "Delete"
        deleteButton.click();

    }

    public boolean isPudelPresentByDataId(String id) {
        try {

            WebElement row = getRowById(id);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getRowById(String id) {
        return webDriver.findElement(By.xpath("//a[contains(@href, 'id=" + id + "')]/ancestor::tr"));
    }

    public void clickEditButtonById(String id) {
        // Находим строку по id
        WebElement row = getRowById(id);
        // Находим кнопку Edit внутри этой строки
        WebElement editButton = row.findElement(By.xpath(".//a[contains(@href, 'id=" + id + "')]"));

        editButton.click();
    }

    public String getNameByDataId(String id) {
        // Находим строку таблицы по id (ссылка на редактирование с нужным id)
        WebElement row = webDriver.findElement(By.xpath("//a[contains(@href, 'id=" + id + "')]/ancestor::tr"));

        // Извлекаем имя из первой ячейки (первая ячейка - имя пуделя)
        WebElement nameCell = row.findElement(By.xpath(".//td[1]"));

        return nameCell.getText();
    }
}
