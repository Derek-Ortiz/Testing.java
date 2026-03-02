import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_Cart_04_Persistencia {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.demoblaze.com");
            String prod = "Iphone 6 32gb";
            System.out.println("--- PRUEBA 04: Persistencia ---");

            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prod))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
            wait.until(ExpectedConditions.alertIsPresent()).accept();

            driver.findElement(By.id("cartur")).click();
            System.out.println("Producto en carrito. Refrescando página (F5)...");
            driver.navigate().refresh();

            boolean sigueAhi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+prod+"']"))).isDisplayed();
            System.out.println("¿El producto sigue presente?: " + (sigueAhi ? "SÍ, persiste correctamente." : "NO, se perdió."));

        } finally {
            driver.quit();
        }
    }
}