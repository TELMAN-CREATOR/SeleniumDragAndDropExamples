import Utility.BaseDriver;
import Utility.MyMethod;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Soru1 extends BaseDriver {
    @Test
    public void Test1() throws InterruptedException {

        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-quiz/drag-drop-quiz-d2.html");
        MyMethod.wait(2);

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);
        for (int i = 1; i <=15 ; i++) {
            WebElement city = driver.findElement(By.cssSelector("[id='a"+i+"']"));
            WebElement destination = driver.findElement(By.cssSelector("[id='q"+i+"']+div"));
            wait.until(ExpectedConditions.elementToBeClickable(city));
            actions.clickAndHold(city).pause(Duration.ofMillis(500)).moveToElement(destination).pause(Duration.ofMillis(500)).release().build().perform();
        }
        driverQuit();
    }
}
