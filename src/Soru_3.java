import Utility.BaseDriver;
import Utility.MyMethod;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Soru_3 extends BaseDriver {

    @Test
    public void Test3(){
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html");

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> boxs = driver.findElements(By.cssSelector("[id^='box']"));
        List<WebElement> cities = driver.findElements(By.cssSelector("[id^='node']"));
        System.out.println(boxs.size() + " " + cities.size());

        for (WebElement city : cities) {
            wait.until(ExpectedConditions.elementToBeClickable(city));

            for (WebElement box : boxs) {
                List<WebElement> answers = box.findElements(By.tagName("li"));

                if (answers.size() == 3) {
                    System.out.println("Bu kutu dolu, geçiliyor: " + box.getAttribute("id"));
                    continue;
                }
                wait.until(ExpectedConditions.visibilityOf(box));
                actions.clickAndHold(city)
                        .pause(Duration.ofMillis(500))
                        .moveToElement(box)
                        .pause(Duration.ofMillis(500))
                        .release(city)
                        .build()
                        .perform();

                MyMethod.wait(1);

                answers = box.findElements(By.cssSelector("li"));
                WebElement lastAnswer = answers.get(answers.size() - 1);
                wait.until(ExpectedConditions.visibilityOf(lastAnswer));

                if (lastAnswer.getAttribute("class").equals("correctAnswer")) {
                    break;
                }
            }
        }
        driverQuit();
    }


}
