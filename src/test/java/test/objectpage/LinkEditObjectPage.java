package test.objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import test.entity.LinkCategory;

/**
 * Created by carlos on 6/30/17.
 */
public class LinkEditObjectPage extends AbstractObjectPage {


    @FindBy(how= How.ID, using="tag-name")
    WebElement txtName;

    @FindBy(how= How.ID, using="tag-slug")
    WebElement txtSlug;

    @FindBy(how= How.ID, using="tag-description")
    WebElement txtDescription;

    @FindBy(how= How.ID, using="submit")
    WebElement bnAddLink;

    @FindBy(how= How.CSS, using = "div.error")
    WebElement divMessage;

    public LinkEditObjectPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void createLink(LinkCategory linkCategory) {
        this.enableAllScreenConfigurations();
        this.txtName.sendKeys(linkCategory.getName());
        this.txtSlug.sendKeys(linkCategory.getSlug());
        this.txtDescription.sendKeys(linkCategory.getDescription());
        this.bnAddLink.click();
    }

    public String getErrorColor()
    {
        return Color.fromString(this.divMessage.getCssValue("background-color")).asHex();
    }

    public String getErrorMessage()
    {
        System.out.print(this.divMessage.getText()+"\n");
        System.out.print(this.divMessage.findElement(By.tagName("p")).getText()+"\n");
        return this.divMessage.getText();
    }
}
