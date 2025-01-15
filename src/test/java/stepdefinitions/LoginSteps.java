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
    static int wait=100;
    static String webUrl = "https://practice.automationtesting.in/my-account/";
    static String usedPassword="";
    static String usedEmail="";


    @BeforeAll
    public static void initiate(){
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
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
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get(webUrl);

        webDriverActions.acceptCookies(webUrl);

        String emailField="#reg_email";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailField)));
        String enteredEmail = webDriverActions.emailGenerator();
        webDriverActions.enterTextInField(webUrl,emailField,enteredEmail);
        System.out.println("Entered email: " + enteredEmail);

        String passwordField="#reg_password";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordField)));
        String enteredPassword=webDriverActions.passwordGenerator();
        webDriverActions.enterTextInField(webUrl,passwordField,enteredPassword);
        System.out.println("Entered password:"+enteredPassword);

        String registerButton="register";
        webDriverActions.clickButton(webUrl,registerButton);
        System.out.println("Button Register is clicked");

        String welcomeUser="//*[@id=\"page-36\"]/div/div[1]/div";
        String findElement=webDriverActions.findElementDoExist(webUrl,welcomeUser);
        assert findElement.contains("Hello");
        System.out.println("User is log in");

        usedPassword.matches(enteredPassword);
        usedEmail.matches(enteredEmail);


    }

    @And("The user navigates to Account Details")
    public void the_user_navigates_to_Account_Details(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String accountDetails="Account Details";
        webDriverActions.clickHyperlink(webUrl,accountDetails);
        System.out.println("Account Details hyperlink is clicked");

    }

    @When("User is under Account Details tab")
    public void user_is_under_account_details_tab(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String tab="//*[@id=\"page-36\"]/div/div[1]/div/form";
        String findElement=webDriverActions.findElementDoExist(webUrl,tab);
        assert findElement.contains("First name");
        System.out.println("Validation user is under the selected Account Details tab");

    }

    @Then("The email address displayed under the Email Address textbox matches the expected email")
    public void the_email_address_displayed_under_the_Email_Address_textbox_matches_the_expected_email(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String email="//*[@id=\"account_email\"]";
        webDriverActions.findElementDoExist(webUrl,email);
        assert email.equals(usedEmail);
        System.out.println("Email matches :"+usedEmail);

    }



}
