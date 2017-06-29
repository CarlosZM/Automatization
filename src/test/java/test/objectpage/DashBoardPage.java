package test.objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by carlos on 6/24/17.
 */
public class DashBoardPage {

    @FindBy(how= How.XPATH,using="(//a[@class='ab-item'])[17]")
    WebElement lblUserNameAuthenticated;

    @FindBy(how= How.ID, using="menu-posts")
    WebElement btnPosts;

    @FindBy(how= How.CLASS_NAME, using="add-new-h2")
    WebElement btnAddPost;

    WebDriver webDriver;

    public DashBoardPage(WebDriver webDriver)
    {
        this.webDriver=webDriver;
    }

    public String getAuthenticatedUserName()
    {
        return this.lblUserNameAuthenticated.getText();
    }

    public void accessToPostSection()
    {
        this.btnPosts.click();
    }

    public void craeteNewPost()
    {
        this.btnAddPost.click();
    }
}
