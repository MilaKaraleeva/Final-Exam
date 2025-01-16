package stepdefinitions;
/*test code, including Cucumber step definitions, will reside.*/

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prgs.bdd.WebDriverActions;
import java.net.CookieHandler;
import java.net.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {

    static WebDriver webDriver;
    static int wait=100;
    static String webUrlStart="https://practice.automationtesting.in/";
    static String webUrl = "https://practice.automationtesting.in/my-account/";
    static String usedPassword="";
    static String usedEmail="";
    static String nameFirst="";
    static String nameLast="";


    @BeforeAll
    public static void initiate(){
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
           // chromeOptions.addArguments("--headless", "--disable-gpu");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-ads");
            webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
        }

    }
    @AfterAll
    public static void tearDown(){
        if (webDriver != null) {
            webDriver.switchTo().defaultContent();
            webDriver.close();
            webDriver.quit();
        }
    }

    @Given("User is under login page")
    public void user_is_under_login_page(){
       WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        webDriver.get(webUrlStart);


        webDriverActions.acceptCookies(webUrlStart);


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Automation Practice Site"));
        System.out.println("Under main page");

    }

    //Scenario 10
    @And("The user is successfully logged in to the web page")
    public void the_user_is_successfully_logged_in_to_the_web_page(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
     //   webDriver.get(webUrl);

        String accountNavigation="My Account";
        webDriverActions.clickHyperlink(webUrl,accountNavigation);

       // webDriverActions.acceptCookies(webUrl);

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

        usedEmail=enteredEmail;

    }

    @When("The user navigates to Account Details")
    public void the_user_navigates_to_Account_Details(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String accountDetails="Account Details";
        webDriverActions.clickHyperlink(webUrl,accountDetails);
        System.out.println("Account Details hyperlink is clicked");

    }

    @And("User Validate correct tab")
    public void user_validate_correct_tab(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String tabXpath="//*[@id=\"page-36\"]/div/div[1]/div/form";
        String findElement=webDriverActions.findElementDoExist(webUrl,tabXpath);
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

    //Scenario 2
    @And("User enters valid first name in the First name textbox")
    public void user_enters_valid_first_name_in_the_First_name_textbox(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String firstNameField="#account_first_name";
        String firstName = webDriverActions.firstnameGenerator();
        webDriverActions.enterTextInField(webUrl,firstNameField,firstName);
        System.out.println("Entered First Name:"+firstName);

        nameFirst=firstName;
    }

    @And("User enters valid last name in the Last name textbox")
    public void user_enters_valid_last_name_in_the_First_name_textbox(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String lastNameField="#account_last_name";
        String lastName = webDriverActions.lastnameGenerator();
        webDriverActions.enterTextInField(webUrl,lastNameField,lastName);
        System.out.println("Entered Last Name:"+lastName);

        nameLast=lastName;
    }

    @Then("The first name displayed under the First name textbox matches the entered name")
    public void the_first_name_displayed_under_the_First_name_textbox_matches_the_entered_name(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String firstName="//*[@id=\"account_first_name\"]";
        webDriverActions.findElementDoExist(webUrl,firstName);
        assert firstName.equals(nameFirst);
        System.out.println("First name matches :"+nameFirst);

    }

    @And("The last name displayed under the Last name textbox matches the entered name")
    public void the_last_name_displayed_under_the_Last_name_textbox_matches_the_entered_name(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String lastName="//*[@id=\"account_last_name\"]";
        webDriverActions.findElementDoExist(webUrl,lastName);
        assert lastName.equals(nameLast);
        System.out.println("Last name matches :"+nameLast);

    }

    @When("User clicks on the Save changes button")
    public void user_clicks_on_the_Save_button(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String saveChangesButton="save_account_details";
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5000));
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(saveChangesButton)));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", buttonElement);

        webDriverActions.clickButton(webUrl,saveChangesButton);
        System.out.println("Save Changes Button is clicked");

    }

    @Then("A success message is displayed confirming the changes were saved")
    public void a_success_message_is_displayed_confirming_the_changes_were_saved(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String successMessage="//*[@id=\"page-36\"]/div/div[1]/div[1]";
        webDriverActions.findElementDoExist(webUrl,successMessage);
        assert successMessage.matches("Account details changed successfully.");
        System.out.println("First name and Last name were saved successfully");

    }

    //Scenario 3
    @When("Clicks on the Logout button")
    public void clicks_on_the_Logout_button(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String logout="Logout";
        webDriverActions.clickHyperlink(webUrl,logout);
        System.out.println("Logout hyperlink is clicked");

    }

    @Then("User is redirected to the login page")
    public void user_is_redirected_to_the_login_page(){
        WebDriverActions webDriverActions=new WebDriverActions(webDriver,wait);

        String login="//*[@id=\"customer_login\"]/div[1]";
        String findElement=webDriverActions.findElementDoExist(webUrl,login);
        assert findElement.contains("Login");
        System.out.println("User is redirected to login page");

    }


}
