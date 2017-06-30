package test.objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import test.entity.User;

import java.util.ArrayList;

/**
 * Created by carlos on 6/30/17.
 */
public class UserEditObjectPage extends AbstractObjectPage {

    @FindBy(  how= How.ID, using="user_login")
    WebElement txtUserName;

    @FindBy(  how= How.ID, using="email")
    WebElement txtEmail;

    @FindBy(  how= How.ID, using="pass1")
    WebElement txtPassword;

    @FindBy(  how= How.ID, using="pass2")
    WebElement txtRepeatedPassword;

    @FindBy(  how= How.ID, using="createusersub")
    WebElement btnAddNewUser;

    @FindBy(  how= How.CLASS_NAME, using="error")
    WebElement divErrors;

    @FindBy(  how= How.ID, using="pass-strength-result")
    WebElement btnStrengthIndicator;

    public UserEditObjectPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void createUser(User user) {
        this.txtUserName.sendKeys(user.getUserName());
        this.txtPassword.sendKeys(user.getPassword());
        this.txtRepeatedPassword.sendKeys(user.getPassword());
        this.txtEmail.sendKeys(user.getEmail());
        this.btnAddNewUser.click();
    }

    public void typePassword(String password)
    {
        this.txtPassword.sendKeys(password);
    }

    public String getErrorColor()
    {
        return Color.fromString(this.divErrors.getCssValue("background-color")).asHex();
    }

    public ArrayList<String> getErrors()
    {
        ArrayList<String> strings=new ArrayList<String>();
        for(WebElement webElement: this.divErrors.findElements(By.tagName("p")))
        {
            strings.add(webElement.getText());
        }
        return strings;
    }

    public String getStrengthIndicatorColor()
    {
        return Color.fromString(this.btnStrengthIndicator.getCssValue("background-color")).asHex();

    }

    public String getStrengthIndicatorLevel()
    {
        return this.btnStrengthIndicator.getText();
    }
}
