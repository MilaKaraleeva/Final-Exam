package prgs.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Random;

public class WebDriverActions {

    private final WebDriver webDriver;
    private final int wait;

    public WebDriverActions(WebDriver webDriver, int wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    //url load + find element + click
    public void clickButton(String webUrl, String htmlButton){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        WebElement enterButton=webDriver.findElement(By.xpath(htmlButton));
        enterButton.click();

    }

    public String emailGenerator(){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        Random rnd = new Random();
        String newEmail= "test.test"+rnd+"@gmail.com";
        while (newEmail.length() <= 20) {
            return newEmail;
        }
        return newEmail;
    }
    public String passwordGenerator(){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        Random rnd = new Random();
        String newPassword= "ABC"+rnd+"&%$";
        while (newPassword.length() <= 20) {
            return newPassword;
        }
        return newPassword;
    }


    //url load + find element + type in
    public String enterTextInField(String webUrl,String htmlField, String textToWrite){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        WebElement inputField = webDriver.findElement(By.xpath(htmlField));
        inputField.clear();
        inputField.sendKeys(textToWrite);
        return inputField.getAttribute("value");
    }

    //url load + find element + get text
    public String findElementDoExist(String webUrl,String elementText ){
        WebDriver.Timeouts timeouts;
        timeouts = webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        webDriver.get(webUrl);
        WebElement messageText=webDriver.findElement(By.xpath(elementText));
        return messageText.getText();
    }


}
