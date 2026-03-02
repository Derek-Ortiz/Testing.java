import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_Cart_02_Multiples {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("https://www.demoblaze.com");
            String[] productos = {"Samsung galaxy s6", "Nokia lumia 1520"};
            System.out.println("--- PRUEBA 02: Múltiples Productos ---");

            for (String p : productos) {
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText(p))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                System.out.println("Añadido: " + p);
                driver.findElement(By.id("nava")).click();
            }

            driver.findElement(By.id("cartur")).click();
            int cantidad = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("success"))).size();
            System.out.println("Total de artículos detectados en la tabla del carrito: " + cantidad);

        } finally {
            driver.quit();
        }
    }
}