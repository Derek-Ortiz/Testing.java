

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SauceDemoTest {
    public static void main(String[] args) {
        // Configuración del driver (Asegúrate de tener el path correcto o usar WebDriverManager)
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Esperar a que cargue el inventario
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_list")));
            System.out.println("Login exitoso.");

            // Seleccionar los primeros 4 botones de "Add to cart"
            List<WebElement> addButtons = driver.findElements(By.className("btn_inventory"));

            for (int i = 0; i < 4; i++) {
                addButtons.get(i).click();
                System.out.println("Artículo " + (i + 1) + " agregado.");
            }

            // Validar carrito
            String cartBadge = driver.findElement(By.className("shopping_cart_badge")).getText();
            System.out.println("Total de artículos en consola: " + cartBadge);

            if (cartBadge.equals("4")) {
                System.out.println("PRUEBA EXITOSA: 4 artículos en el carrito.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}