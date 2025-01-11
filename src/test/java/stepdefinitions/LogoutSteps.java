package stepdefinitions;

import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LogoutSteps {

    static WebDriver webDriver;
    static int wait=5;
    static String webUrl = "https://demo.automationtesting.in/SignIn.html";

}
