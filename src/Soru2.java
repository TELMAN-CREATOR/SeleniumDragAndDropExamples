import Utility.BaseDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Soru2 extends BaseDriver {

    /*
    http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html
         buradaki bütün öğrencileri bütün kutucukları doldurana kadar atınız.
     */
    @Test
    public void Test2(){

        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html");

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> students = driver.findElements(By.cssSelector("[id^='node']"));
        List<WebElement> teams = driver.findElements(By.cssSelector("[id^='box']"));

        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        wait.until(ExpectedConditions.visibilityOfAllElements(teams));

        for (int i = 0; i < students.size(); i++) {
            WebElement student = students.get(i);
            WebElement team = teams.get(i % teams.size());

            actions.clickAndHold(student)
                    .pause(Duration.ofMillis(500))
                    .moveToElement(team)
                    .pause(Duration.ofMillis(500))
                    .release()
                    .build()
                    .perform();

            System.out.println(student.getText() + " sürüklendi ve bırakıldı.");
        }

        driverQuit();
    }
}
