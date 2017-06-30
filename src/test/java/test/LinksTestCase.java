package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import test.entity.LinkCategory;
import test.entity.User;
import test.objectpage.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by carlos on 6/30/17.
 */
public class LinksTestCase {

    private WebDriver webDriver;

    private String path;

    private LoginObjectPage loginObjectPage;

    private DashBoardPage dashBoardPage;

    private LinkEditObjectPage linkEditPage;

    private User user;

    @Before
    public void setup() throws Exception
    {
        System.setProperty("webdriver.firefox.marionette","/home/carlos/Downloads/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.user=new User("admin","demo123");
        this.path= "http://demosite.center/wordpress/wp-login.php";
        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.loginObjectPage= PageFactory.initElements(this.webDriver,LoginObjectPage.class);
        this.dashBoardPage=PageFactory.initElements(this.webDriver,DashBoardPage.class);
        this.webDriver.get(this.path);
        this.loginObjectPage.authenticateUser(this.user);
        this.linkEditPage=PageFactory.initElements(this.webDriver,LinkEditObjectPage.class);
    }

    @After
    public void tearDown() throws Exception {
        this.webDriver.quit();
    }

    /**
     * Code: WPS_L_01
     */
    @Test
    public void testCreateLinkWithExistingData()
    {
        this.dashBoardPage.accessToSection("Links","Link Categories");
        LinkCategory linkCategory=new LinkCategory("Blogroll","blogroll","repeated link");
        this.linkEditPage.createLink(linkCategory);
        assertEquals("A term with the name provided already exists.",this.linkEditPage.getErrorMessage());
        assertEquals("#ffebe8",this.linkEditPage.getErrorColor());

    }
}
