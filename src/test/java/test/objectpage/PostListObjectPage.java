package test.objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Scanner;

/**
 * Created by carlos on 6/29/17.
 */
public class PostListObjectPage extends AbstractObjectPage{

    @FindBy(how= How.CSS, using="ul.subsubsub>li>a")
    List<WebElement> lstPostsTypes;

    @FindBy(how= How.CSS, using="tbody#the-list>tr")
    List<WebElement> lstPosts;

    @FindBy(how= How.ID, using="post-search-input")
    WebElement searchText;

    @FindBy(how= How.ID, using="search-submit")
    WebElement btnSearchText;

    @FindBy(how= How.NAME, using="action")
    WebElement lstActions;

    @FindBy(how= How.ID, using="doaction")
    WebElement btnApply;

    public PostListObjectPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void showPostsType(String type)
    {
        this.enableAllScreenConfigurations();
        for(WebElement webElement:this.lstPostsTypes)
        {
            if(webElement.getText().split(" ")[0].equals(type) ||webElement.getText().split(" ")[0].equals(type+"s") )
            {
                webElement.click();
                break;
            }
        }
    }

    public int selectPostByTitle(String titlePostToDelete) {
        this.searchText.sendKeys(titlePostToDelete);
        this.btnSearchText.click();
        int selectedAmount=0;
        for(WebElement webElement:this.lstPosts)
        {
            System.out.print(webElement.findElement(By.cssSelector(".column-title>strong>a")).getText()+"\n");
            if(webElement.findElement(By.cssSelector(".column-title>strong>a")).getText().equals(titlePostToDelete))
            {
                selectedAmount++;
                webElement.findElement(By.cssSelector(".check-column>input")).click();
            }
        }
        return selectedAmount;
    }

    public void deleteSelectedPosts() {
        new Select(this.lstActions).selectByVisibleText("Move to Trash");
        this.btnApply.click();
    }

    public int getAmountByCategory(String category) {
        for(WebElement webElement: this.lstPostsTypes)
        {
            if(webElement.getText().split(" ")[0].equals(category)|| webElement.getText().split(" ")[0].equals(category+"s")){
                return new Scanner(webElement.getText()).useDelimiter("[^0-9]+").nextInt();
            }
        }
        System.out.print(category+"missed \n");
        return 0;
    }
}
