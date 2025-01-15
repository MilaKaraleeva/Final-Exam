package prgs.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;


public class WebDriverActions {

    private final WebDriver webDriver;
    private final int wait;



    public WebDriverActions(WebDriver webDriver, int wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    //Accept Cookies
    public void acceptCookies(String webUrl) {
        try {
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

    //Click using name of Button
    public void clickButton(String webUrl, String htmlButton){

        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            WebElement enterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(htmlButton)));
            Thread.sleep(5000);
            enterButton.click();
        }
        catch (Exception e) {
            System.err.println("Error locating or clicking the button: " + e.getMessage());

        }

    }

    //Click using xpath of Button
    public void click(String webUrl, String html){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(html)));
            Thread.sleep(1000);
            element.click();
        }
        catch (Exception e) {
            System.err.println("Error locating or clicking the button: " + e.getMessage());

        }

    }

    //Click hyperlink using linkText
    public void clickHyperlink(String webUrl, String hyperlink){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            WebElement selectHyperlink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(hyperlink)));
            selectHyperlink.click();
        }
        catch (Exception e) {
            System.err.println("Error locating or clicking the hyperlink: " + e.getMessage());

        }

    }

    //Write in Field, find by cssSelector
    public String enterTextInField(String webUrl, String idField, String textToWrite){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(idField)));
            Thread.sleep(1000);
            if (!Objects.requireNonNull(inputField.getAttribute("value")).isEmpty())
            {
                inputField.clear();
            }
            inputField.sendKeys(textToWrite);
            return inputField.getAttribute("value");
        }
        catch (Exception e)
        {
            System.err.println("Error locating or interacting with the element: " + e.getMessage());

        }
        return webUrl;
    }

    //Element is located and the text is read by className
    public String findElementDoExist(String webUrl,String elementText ){

        WebElement messageText=webDriver.findElement(By.xpath(elementText));
        return messageText.getText();
    }

    public String emailGenerator(){
        Random rnd = new Random();
        try
        {
            return "test" + rnd.nextInt(100000) + "@gmail.com";
        } catch (Exception e){
            System.err.println("Error generating the email: " + e.getMessage());
            throw e;
        }
    }
    public String passwordGenerator(){
        Random rnd = new Random();
        try
        {
            return "ABC"+rnd.nextInt(100000)+"&%$";
        } catch (Exception e) {
            System.err.println("Error generating the email: " + e.getMessage());
            throw e;
        }
    }

    public String firstnameGenerator(){
        Random rnd = new Random();
        try
        {
            return "First"+rnd.nextInt(100000)+"Name";
        } catch (Exception e) {
            System.err.println("Error generating First Name: " + e.getMessage());
            throw e;
        }
    }

    public String lastnameGenerator(){
        Random rnd = new Random();
        try
        {
            return "Last"+rnd.nextInt(100000)+"Name";
        } catch (Exception e) {
            System.err.println("Error generating Last Name: " + e.getMessage());
            throw e;
        }
    }

}
