import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_Cart_03_Eliminar {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.demoblaze.com");
            String prod = "Nexus 6";
            System.out.println("--- PRUEBA 03 ---");


            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prod))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
            wait.until(ExpectedConditions.alertIsPresent()).accept();

            driver.findElement(By.id("cartur")).click();
            System.out.println("Eliminando " + prod + " del carrito...");


            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='"+prod+"']/..//a"))).click();

            boolean eliminado = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[text()='"+prod+"']")));
            if(eliminado) System.out.println("Resultado: El producto ya no existe en el carrito.");

        } finally {
            driver.quit();
        }
    }
}