package test.objectpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by carlos on 6/29/17.
 */
public class AbstractObjectPage {

    protected WebDriver webdriver;

    protected WebDriverWait webDriverWait;


    @FindBy(how=How.CSS,using=".metabox-prefs>label>input")
    private List<WebElement> lstRbtSettings;

    @FindBy(how= How.ID,using="show-settings-link")
    private WebElement btnScreenOptions;

    public AbstractObjectPage(WebDriver webdriver)
    {
        this.webdriver=webdriver;
        this.webDriverWait=new WebDriverWait(webdriver,10);
    }

    protected void waitToReloadNextPage()
    {
        this.webDriverWait.until(new
                                         ExpectedCondition<Boolean>() {
                                             public Boolean apply(WebDriver driver) {
                                                 return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                                             }
                                         });
    }

    protected void simpleWaitInSeconds(int seconds)
    {
        try{
            Thread.sleep(seconds*1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void enableAllScreenConfigurations()
    {
        this.btnScreenOptions.click();
        for(WebElement webElement: this.lstRbtSettings)
        {
            if(!webElement.isSelected())
            {
                webElement.click();
            }
        }
        this.btnScreenOptions.click();

    }
}
