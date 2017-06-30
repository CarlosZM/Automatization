package test.objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.entity.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 6/29/17.
 */
public class PostEditionObjectPage extends AbstractObjectPage{

    @FindBy(how= How.ID, using="title")
    WebElement txtTitle;

    @FindBy(how= How.TAG_NAME, using="p")
    WebElement txtContent;

    @FindBy(how= How.CSS, using="#categorychecklist>li>label")
    List<WebElement> lstCategories;

    @FindBy(how= How.ID, using="save-post")
    WebElement btnSaveDraft;

    @FindBy(how= How.ID, using = "message")
    WebElement divMessage;

    @FindBy(how= How.ID, using="post-preview")
    WebElement btnPreview;


    public PostEditionObjectPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void createDraftPost(Post post)
    {
        this.enableAllScreenConfigurations();
        this.writeTextInContentFrame(post.getContent());
        this.txtTitle.sendKeys(post.getName());
        for (WebElement webElement:this.lstCategories)
        {
            if(post.categoriesNames().contains(webElement.getText()))
            {
                webElement.click();
            }
        }
        this.btnSaveDraft.click();
        this.waitToReloadNextPage();
    }

    private void writeTextInContentFrame(String content)
    {
        this.webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        ((JavascriptExecutor)this.webdriver).executeScript("document.getElementsByTagName('p')[0].innerHTML = '"+content+"'");
        this.webdriver.switchTo().defaultContent();
    }

    public String getMessageColor()
    {
        return Color.fromString(this.divMessage.getCssValue("background-color")).asHex();
    }

    public String getMessage()
    {
        return this.divMessage.getText();
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories=new ArrayList<String>();
        for (WebElement webElement:this.lstCategories)
        {
            if(webElement.isSelected())
            {
                categories.add(webElement.getText());
            }
        }
        return categories;
    }

    public void showDraftPreview()
    {
        this.btnPreview.click();
        this.simpleWaitInSeconds(1);
        this.webdriver.switchTo().window((String) this.webdriver.getWindowHandles().toArray()[1]);
    }

    public void returnDefaultControl() {
        this.webdriver.switchTo().defaultContent();
    }
}
