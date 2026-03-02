import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_Cart_01_Agregar {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.demoblaze.com");
            driver.manage().window().maximize();


            WebElement prodLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6")));
            String nombreProd = prodLink.getText();
            System.out.println("--- PRUEBA 01 ---");
            System.out.println("Intentando añadir: " + nombreProd);
            prodLink.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();


            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            System.out.println("¡" + nombreProd + " se añadió correctamente!");

            driver.findElement(By.id("cartur")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+nombreProd+"']")));
            System.out.println("Confirmado: El producto aparece físicamente en el carrito.");

        } finally {
            driver.quit();
        }
    }
}