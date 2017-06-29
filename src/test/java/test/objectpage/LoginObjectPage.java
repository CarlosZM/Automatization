package test.objectpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by carlos on 6/24/17.
 */
public class LoginObjectPage {

    @FindBy(how= How.ID, using="user_login")
    WebElement txtUserName;

    @FindBy(how=How.ID, using="user_pass")
    WebElement txtPassword;

    @FindBy(how=How.ID, using="wp-submit")
    WebElement btnLogin;

    WebDriver webDriver;

    WebDriverWait webDriverWait;

    public LoginObjectPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait=new WebDriverWait(webDriver,5);
    }

    public void typeUserName(String userName)
    {
        this.txtUserName.clear();
        this.txtUserName.sendKeys(userName);
    }

    public void typePassword(String password)
    {
        this.txtPassword.clear();
        this.txtPassword.sendKeys(password);
    }

    public void sendCredentials()
    {
        this.btnLogin.click();
    }

    public void authenticateUser(String userName, String password)
    {
        this.typeUserName(userName);
        this.typePassword(password);
        this.sendCredentials();
    }

    public void waitForLoginRedirect()
    {
        this.webDriverWait.until(new
                           ExpectedCondition<Boolean>() {
                               public Boolean apply(WebDriver driver) {
                                   return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                               }
                           });
    }


}
