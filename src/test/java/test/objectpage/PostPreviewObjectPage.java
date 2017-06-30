package test.objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;

/**
 * Created by carlos on 6/29/17.
 */
public class PostPreviewObjectPage extends AbstractObjectPage {


    @FindBy(how = How.TAG_NAME, using ="h2")
    WebElement lblTitle;

    @FindBy(how = How.CLASS_NAME, using ="entry")
    WebElement lblContent;

    @FindBy(how = How.CSS, using =".postmeta>span:nth-child(1)")
    WebElement lblPosted;

    @FindBy(how = How.CSS, using =".postmeta>span:nth-child(3)")
    WebElement lblCategories;

    public PostPreviewObjectPage(WebDriver webdriver) {
        super(webdriver);
    }

    public String getTitle() {
        return this.lblTitle.getText();
    }

    public String getContent() {
        return this.lblContent.getText();
    }

    public String[] getCategoriesNames() {
        return this.lblCategories.getText().split(",");
    }

    public String getUserName() {
        return this.lblPosted.getText().split(" ")[2];
    }
}
