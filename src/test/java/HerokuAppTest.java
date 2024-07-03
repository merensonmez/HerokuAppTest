import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HerokuAppTest {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    driver.get("https://the-internet.herokuapp.com/");

        WebElement formAuthentication = driver.findElement(By.cssSelector("a[href='/login']"));
        formAuthentication.click();

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        loginButton.click();

        boolean isLoginSuccessful = driver.findElement(By.cssSelector("#flash")).isDisplayed();
        if (isLoginSuccessful) {
            System.out.println("Login successful!");
        }else {
            System.out.println("Login NOT successful.`");
        }
        WebElement logoutButton = driver.findElement(By.cssSelector(".button.secondary.radius"));
        logoutButton.click();

        WebElement wrongUsername = driver.findElement(By.id("username"));
        wrongUsername.sendKeys("ErenSonmez(Wrong)");
        WebElement wrongPassword = driver.findElement(By.id("password"));
        wrongPassword.sendKeys("TestAutomation(Wrong)");
        WebElement loginButton2 = driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        loginButton2.click();

        boolean isLoginFailed = driver.findElement(By.cssSelector("#flash")).isDisplayed();
        if (isLoginFailed){
            WebElement errorMessage = driver.findElement(By.id("flash"));
            System.out.println("Error message: " +errorMessage.getText());
        }else {
            System.out.println("Failed to display an error input message.");
        }
        TakesScreenshot tss = (TakesScreenshot) driver;
        String output = System.getProperty("user.dir")+"//Screenshot//ErrorMessage.png";
        File fullPageSS = new File(output);

        File temporarySS = tss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temporarySS,fullPageSS);
    }
}
