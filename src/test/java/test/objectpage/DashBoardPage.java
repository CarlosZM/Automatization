package test.objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by carlos on 6/24/17.
 */
public class DashBoardPage extends AbstractObjectPage {

    Actions actionBuilder;

    @FindBy(how= How.XPATH,using="(//a[@class='ab-item'])[17]")
    WebElement lblUserNameAuthenticated;

    @FindBy(  how= How.ID, using="menu-posts")
    WebElement btnPost;

    @FindBy(how= How.CSS, using="#menu-posts>.wp-submenu>.wp-submenu-wrap>ul>li")
    List<WebElement> lstPostsOptions;

    WebDriver webDriver;

    @FindBy(  how= How.ID, using="menu-users")
    WebElement btnUser;

    @FindBy(how= How.CSS, using="#menu-users>.wp-submenu>.wp-submenu-wrap>ul>li")
    List<WebElement> lstUsersOptions;

    @FindBy(  how= How.ID, using="menu-links")
    WebElement btnLinks;

    @FindBy(how= How.CSS, using="#menu-links>.wp-submenu>.wp-submenu-wrap>ul>li")
    List<WebElement> lstLinksOptions;

    public DashBoardPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver=webDriver;
        this.actionBuilder= new Actions(this.webDriver);
    }

    public String getAuthenticatedUserName()
    {
        return this.lblUserNameAuthenticated.getText();
    }

    public void accessToSection(String section, String subSection) {
        this.enableAllScreenConfigurations();
        if(section.equals("Posts"))
        {
            this.actionBuilder.moveToElement(this.btnPost).build().perform();
            this.simpleWaitInPage(2);
            this.accessToSubSection(this.lstPostsOptions,subSection);
        }
        else if(section.equals("Users"))
        {
            this.actionBuilder.moveToElement(this.btnUser).build().perform();
            this.simpleWaitInPage(2);
            this.accessToSubSection(this.lstUsersOptions,subSection);
        }
        else if(section.equals("Links"))
        {
            this.actionBuilder.moveToElement(this.btnLinks).build().perform();
            this.simpleWaitInPage(2);
            this.accessToSubSection(this.lstLinksOptions,subSection);
        }
    }

    private void accessToSubSection(List <WebElement> listOption,String name)
    {
        for(WebElement item:listOption)
        {
            if(item.getText().equals(name)){
                item.click();
                break;
            }
        }
    }

    private void simpleWaitInPage(int seconds)
    {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
