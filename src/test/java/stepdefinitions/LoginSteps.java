package stepdefinitions;
/*test code, including Cucumber step definitions, will reside.*/

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prgs.bdd.WebDriverActions;
import java.net.CookieHandler;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {

    static WebDriver webDriver;
    static int wait=10;
    static String webUrl = "https://practice.automationtesting.in/my-account/";
    static String element;
    static String myAccount="<a href=\"https://practice.automationtesting.in/my-account/\">My Account</a>";
    static String accountDetails="<a href=\"https://practice.automationtesting.in/my-account/edit-account/\">Account Details</a>";


    @BeforeAll
    public static void initiate(){
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            webDriver = new ChromeDriver(chromeOptions);
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
        }

    }
    @AfterAll
    public static void tearDown(){
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

    //Login for Test User Details feature Background steps Login and Navigate under "My Account"
    @Given("The user is successfully logged in to the web page")
    public void the_user_is_successfully_logged_in_to_the_web_page(){

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fc-button fc-cta-consent fc-primary-button")));
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,10);
        webDriver.get(webUrl);
        webDriverActions.acceptCookies(webUrl);
        String emailField="//*[@id=\"reg_email\"]";
        webDriverActions.enterTextInField(webUrl,emailField,webDriverActions.emailGenerator());
        String passwordField="//*[@id=\"reg_password\"]";
        webDriverActions.enterTextInField(webUrl,passwordField,webDriverActions.passwordGenerator());
        String registerButton="";
        webDriverActions.clickButton(webUrl,registerButton);
        String loadImage="";
        String findElement=webDriverActions.findElementDoExist(webUrl,loadImage);
        assert findElement.contains("Shop Selenium Books");
    }


   /* private static WebDriverActions getEmail() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_password")));
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,10);
        webDriver.get(webUrl);
        String emailField="//*[@id=\"reg_email\"]";
        webDriverActions.enterTextInField(webUrl,emailField,webDriverActions.emailGenerator());
        return webDriverActions;
    }
    private static WebDriverActions getPassword() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_password")));
        String passwordField="//*[@id=\"reg_password\"]";
        webDriverActions.enterTextInField(webUrl,passwordField,webDriverActions.passwordGenerator());
        return webDriverActions;
    }*/





    @When("The user clicks on the {string} link")
    public void the_user_clicks_on_the_My_Account_link(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        webDriverActions.clickButton(webUrl,myAccount);
        String findElement=webDriverActions.findElementDoExist(webUrl,element);
        assert findElement.contains("");

    }
    @And("The user navigates to {string}")
    public void the_user_navigates_to_Account_Details(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        webDriverActions.clickButton(webUrl,accountDetails);
        String findElement=webDriverActions.findElementDoExist(webUrl,element);
        assert findElement.contains("");
    }

    @Then("The email address displayed under the string textbox matches the expected email")
    public void the_email_address_displayed_under_the_Email_Address_textbox_matches_the_expected_email(){
        element="";
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        webDriverActions.findElementDoExist(webUrl,element);


    }



}
