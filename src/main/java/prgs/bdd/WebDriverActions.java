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
        try {
           // webDriver.get(webUrl);
            WebElement acceptCookiesButton = webDriver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button"));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            System.out.println("No cookies prompt found or an error occurred: " + e.getMessage());
            throw e;
        }
    }

    //url load + find element + click
    public void clickButton(String webUrl, String htmlButton){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement enterButton = wait.until(ExpectedConditions.elementToBeClickable(By.name(htmlButton)));
            enterButton.click();
        }
        catch (Exception e) {
            System.err.println("Error locating or clicking the button: " + e.getMessage());
            throw e;
        }

    }

    public String enterTextInField(String webUrl, String idField, String textToWrite) {
        try {
            if (!idField.startsWith("#") && !idField.startsWith(".")) {
                idField = "#" + idField;
            }
            WebElement inputField = webDriver.findElement(By.cssSelector(idField));
            if (!inputField.getAttribute("value").isEmpty()) {
                inputField.clear();
            }
            inputField.sendKeys(textToWrite);
            return inputField.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error locating or interacting with the element: " + e.getMessage());
            throw e;
        }
    }

    //url load + find element + get text
    public String findElementDoExist(String webUrl,String elementText ){
       // webDriver.get(webUrl);
        WebElement messageText=webDriver.findElement(By.className(elementText));
        return messageText.getText();
    }

    public String emailGenerator(){
        Random rnd = new Random();
        try {
            String newEmail = "test" + rnd.nextInt(100000) + "@gmail.com";
            //if (newEmail.length() <= 20) {return newEmail;}
            return newEmail;
        } catch (Exception e){
            System.err.println("Error generating the email: " + e.getMessage());
            throw e;
        }
    }
    public String passwordGenerator(){
        Random rnd = new Random();
        try {
            String newPassword= "ABC"+rnd.nextInt(100000)+"&%$";
           // if (newPassword.length() <= 20) {return newPassword;}
            return newPassword;
        } catch (Exception e) {
            System.err.println("Error generating the email: " + e.getMessage());
            throw e;
        }
    }

}
