import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by savenok_a on 20.03.2017.
 */
public class SeleniumTest {

    @Test
    public void openOnlinerTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WebDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.onliner.by/");

        Thread.sleep(5000);

        driver.quit();
    }

    @Test
    public void invalidLoginMailruTest() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WebDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();
        String initialTitle = driver.getTitle();

//        By loginFieldLocator = By.id("mailbox__login");
//        WebElement loginField = driver.findElement(loginFieldLocator);
        WebElement loginField = driver.findElement(By.id("mailbox__login")); //Оптимизация кода написанного вверху

        By passwordFieldLocator = By.id("mailbox__password");
        WebElement passwordField = driver.findElement(passwordFieldLocator);

        By loginButtonLocator = By.id("mailbox__auth__button");
        WebElement loginButton = driver.findElement(loginButtonLocator);

        By invalidLoginDataLocator = By.cssSelector("[id='mailbox:authfail']");
        WebElement invalidLoginDataLabel = driver.findElement(invalidLoginDataLocator);

        loginField.sendKeys("blablabla");
        passwordField.sendKeys("123431");
        loginButton.click();

        String finallyTitle = driver.getTitle();

        Assert.assertNotEquals(initialTitle, finallyTitle, "Titles are equals");

        driver.quit();
    }

    @Test
    public void loginMailTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WebDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();

        WebElement loginField = driver.findElement(By.id("mailbox__login"));

        WebElement passwordField = driver.findElement(By.id("mailbox__password"));

        WebElement loginButton = driver.findElement(By.id("mailbox__auth__button"));

        loginField.sendKeys("selenium22032017");
        passwordField.sendKeys("Password1*");
        loginButton.click();

        Thread.sleep(5000);

        WebElement emailField = driver.findElement(By.id("PH_user-email"));

        Assert.assertTrue(emailField.isDisplayed());

        driver.quit();
    }
}
