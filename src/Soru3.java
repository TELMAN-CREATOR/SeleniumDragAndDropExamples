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

public class Soru3 extends BaseDriver {

    /*
    Ödev 3: http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html
         buradaki bütün şehirleri bütün ülkere doğru şekilde topluca dağıtınız.
     */
    @Test
    public void Test3(){
        // Not Kod yavaş çalışıyor lütfen çalıştırdıkdan sonra biraz sabırlı olun
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html");
        Actions actions=new Actions(driver);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
       List<WebElement> boxs=driver.findElements(By.cssSelector("[id^='box']"));
       List<WebElement>cities=driver.findElements(By.cssSelector("[id^='node']"));
        System.out.println(boxs.size()+" "+cities.size());

        for (int i = 0; i <21 ; i++) {
            WebElement city= cities.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(city));


            for (int j = 0; j <7 ; j++) {
                WebElement box= boxs.get(j);
                wait.until(ExpectedConditions.visibilityOf(box));
                List<WebElement> answers=driver.findElements(By.cssSelector("[id^='box"+(j+1)+"']>li"));
                if (answers.size()==3)
                    continue;
                actions.clickAndHold(city).pause(Duration.ofMillis(500)).moveToElement(box).pause(Duration.ofMillis(500)).release(city).build().perform();
                MyMethod.wait(1);
                 answers=driver.findElements(By.cssSelector("[id^='box"+(j+1)+"']>li"));
                WebElement answer= answers.get(answers.size()-1);
                wait.until(ExpectedConditions.visibilityOf(answer));

                if (answer.getAttribute("class").equals("correctAnswer"))
                    break;

            }

        }
        driverQuit();
    }

}