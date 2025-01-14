package prgs.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class WebDriverActions {

    private final WebDriver webDriver;
    private final int wait;



    public WebDriverActions(WebDriver webDriver, int wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void acceptCookies(String webUrl) {
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        try {
            WebElement acceptCookiesButton = webDriver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button"));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            System.out.println("No cookies prompt found or an error occurred: " + e.getMessage());
        }
    }

    //url load + find element + click
    public void clickButton(String webUrl, String htmlButton){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        WebElement enterButton=webDriver.findElement(By.cssSelector(htmlButton));
        enterButton.click();

    }

    //url load + find element + type in
    public String enterTextInField(String webUrl,String idField, String textToWrite){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        if (!idField.startsWith("#") && !idField.startsWith(".")) {
            idField = "#" + idField;
        }
        try {
            WebElement inputField = webDriver.findElement(By.id(idField));
            inputField.clear();
            inputField.sendKeys(textToWrite);
            return inputField.getDomAttribute("value");
        }catch (Exception e) {
            System.err.println("Error locating or interacting with the element: " + e.getMessage());
            throw e;
        }

    }

    //url load + find element + get text
    public String findElementDoExist(String webUrl,String elementText ){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        WebElement messageText=webDriver.findElement(By.xpath(elementText));
        return messageText.getText();
    }

    public String emailGenerator(){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        Random rnd = new Random();
        String newEmail= "test.test"+rnd+"@gmail.com";
        while (newEmail.length() <= 20) {
            return newEmail;
        }
        return newEmail.toString();
    }
    public String passwordGenerator(){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        Random rnd = new Random();
        String newPassword= "ABC"+rnd+"&%$";
        while (newPassword.length() <= 20) {
            return newPassword;
        }
        return newPassword.toString();
    }

}
