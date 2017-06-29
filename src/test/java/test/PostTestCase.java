package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import test.objectpage.DashBoardPage;
import test.objectpage.LoginObjectPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by carlos on 6/24/17.
 */
public class PostTestCase {

    private WebDriver webDriver;

    private String path;

    private LoginObjectPage loginObjectPage;

    private DashBoardPage dashBoardPage;

    @Before
    public void setup() throws Exception
    {
        System.setProperty("webdriver.firefox.marionette","/home/carlos/Downloads/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.path= "http://demosite.center/wordpress/wp-login.php";
        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.loginObjectPage= PageFactory.initElements(this.webDriver,LoginObjectPage.class);
    }

    @Test
    public void testSuccessCreationPost()
    {
        this.webDriver.get(this.path);
        this.loginObjectPage.authenticateUser("admin","demo123");
        this.loginObjectPage.waitForLoginRedirect();
        this.dashBoardPage= PageFactory.initElements(this.webDriver,DashBoardPage.class);
        this.dashBoardPage.accessToPostSection();
    }


    @After
    public void tearDown() throws Exception {
        //this.webDriver.quit();
    }
}
