package test.objectpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import test.entity.Category;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 6/29/17.
 */
public class CategoriesObjectPage extends AbstractObjectPage{

    @FindBy(how= How.ID, using="tag-name")
    WebElement txtName;

    @FindBy(how= How.ID, using="tag-slug")
    WebElement txtSlug;

    @FindBy(how= How.ID, using="tag-description")
    WebElement txtDescription;

    @FindBy(how= How.ID, using="submit")
    WebElement btnAddNewCategory;

    @FindBy(how= How.CLASS_NAME, using="displaying-num")
    WebElement lblCategoriesCount;

    @FindBy(how= How.CSS, using="td.column-name")
    List<WebElement> lstName;

    @FindBy(how= How.CSS, using="td.column-description")
    List<WebElement> lstDescription;

    @FindBy(how= How.CSS, using="td.column-slug")
    List<WebElement> lstSlug;

    @FindBy(how= How.CSS, using="td.column-posts")
    List<WebElement> lstPosts;

    @FindBy(how= How.CSS, using="tbody>tr>th.check-column>input")
    List<WebElement> lstRadioButtons;

    @FindBy(how= How.ID, using="tag-search-input")
    WebElement searchText;

    @FindBy(how= How.ID, using="search-submit")
    WebElement btnSearchText;

    @FindBy(how= How.NAME, using="action")
    WebElement lstActions;

    @FindBy(how= How.ID, using="doaction")
    WebElement btnApply;

    @FindBy(how= How.ID, using = "message")
    WebElement divMessage;

    public CategoriesObjectPage(WebDriver webDriver)
    {
        super(webDriver);
    }

    public void createCategory(Category category)
    {
        this.txtName.sendKeys(category.getName());
        this.txtDescription.sendKeys(category.getDescription());
        this.txtSlug.sendKeys(category.getSlug());
        this.btnAddNewCategory.click();
    }

    public String getAmountCategories()
    {
        return this.lblCategoriesCount.getText();
    }

    public String getName()
    {
        return this.lstName.get(0).getText();
    }

    public String getSlug()
    {
        return this.lstSlug.get(0).getText();
    }
    public String getDescription()
    {
        return this.lstDescription.get(0).getText();
    }

    public int getPosts()
    {
        return Integer.parseInt(this.lstPosts.get(0).getText());
    }

    public int gePosts(String name) {
        int i =0;
        for(WebElement webElement: this.lstName)
        {
            if(webElement.getText().equals(name))
            {
                return Integer.parseInt(this.lstPosts.get(i).getText());
            }
            i++;
        }
        return 0;
    }

    public int selectRecordsByName(String name) {
        this.enableAllScreenConfigurations();
        this.searchText.sendKeys(name);
        this.btnSearchText.click();
        int selectedRecords=0;
        int i=0;
        for(WebElement webElement:this.lstName)
        {
            if(webElement.getText().equals(name)){
                this.lstRadioButtons.get(i).click();
                selectedRecords++;
            }
            i++;
        }
        return selectedRecords;
    }

    public void deleteSelectedCategories() {
        new Select(this.lstActions).selectByVisibleText("Delete");
        this.btnApply.click();
    }

    public String getMessage() {
        return this.divMessage.getText();
    }

    public String getMessageColor()
    {
        return Color.fromString(this.divMessage.getCssValue("background-color")).asHex();
    }

    public ArrayList<String>getCategoriesNames() {
        ArrayList<String> strings=new ArrayList<String>();
        for (WebElement webElement:this.lstName)
        {
            strings.add(webElement.getText());
        }
        return strings;
    }
}
