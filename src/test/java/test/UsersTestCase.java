package test;

import com.gargoylesoftware.htmlunit.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import test.entity.User;
import test.objectpage.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by carlos on 6/30/17.
 */
public class UsersTestCase {

    private WebDriver webDriver;

    private String path;

    private LoginObjectPage loginObjectPage;

    private DashBoardPage dashBoardPage;

    private CategoriesObjectPage categoryPage;

    private PostEditionObjectPage postEditionPage;

    private PostPreviewObjectPage previewPage;

    private PostListObjectPage postListPage;

    private UserEditObjectPage userEditPage;

    private User user;

    @Before
    public void setup() throws Exception
    {
        System.setProperty("webdriver.firefox.marionette","/home/carlos/Downloads/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.user=new User("admin","demo123","sysop@opensourcecms.com");
        this.path= "http://demosite.center/wordpress/wp-login.php";
        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.loginObjectPage= PageFactory.initElements(this.webDriver,LoginObjectPage.class);
        this.dashBoardPage=PageFactory.initElements(this.webDriver,DashBoardPage.class);
        this.categoryPage=PageFactory.initElements(this.webDriver,CategoriesObjectPage.class);
        this.postEditionPage=PageFactory.initElements(this.webDriver,PostEditionObjectPage.class);
        this.webDriver.get(this.path);
        this.loginObjectPage.authenticateUser(this.user);
        this.previewPage= PageFactory.initElements(this.webDriver, PostPreviewObjectPage.class);
        this.postListPage=PageFactory.initElements(this.webDriver,PostListObjectPage.class);
        this.userEditPage= PageFactory.initElements(this.webDriver,UserEditObjectPage.class);
    }
    @After
    public void tearDown() throws Exception {
        this.webDriver.quit();
    }

    /**
     * CODE:WPS_U_01
     */
    @Test
    public void testCreationExistingUser()
    {
        this.dashBoardPage.accessToSection("Users","Add New");
        this.userEditPage.createUser(this.user);
        assertArrayEquals(Arrays.asList("ERROR: This username is already registered. Please choose another one.","ERROR: This email is already registered, please choose another one.").toArray(),this.userEditPage.getErrors().toArray());
        assertEquals("#ffebe8",this.userEditPage.getErrorColor());
    }


    /**
     * CODE:WPS_U_02
     */
    @Test
    public void testVeryWeakPassword()
    {
        this.dashBoardPage.accessToSection("Users","Add New");
        this.userEditPage.typePassword("abc");
        assertEquals("#ffa0a0",this.userEditPage.getStrengthIndicatorColor());
        assertEquals("Very weak",this.userEditPage.getStrengthIndicatorLevel());
    }


    /**
     * CODE:WPS_U_03
     */
    @Test
    public void testWeakPassword()
    {
        this.dashBoardPage.accessToSection("Users","Add New");
        this.userEditPage.typePassword("123abcd");
        assertEquals("#ffb78c",this.userEditPage.getStrengthIndicatorColor());
        assertEquals("Weak",this.userEditPage.getStrengthIndicatorLevel());
    }


    /**
     * CODE:WPS_U_04
     */
    @Test
    public void testMediumPassword()
    {
        this.dashBoardPage.accessToSection("Users","Add New");
        this.userEditPage.typePassword("abcd1234");
        assertEquals("#ffec8b",this.userEditPage.getStrengthIndicatorColor());
        assertEquals("Medium",this.userEditPage.getStrengthIndicatorLevel());
    }


    /**
     * CODE:WPS_U_05
     */
    @Test
    public void testStrongPassword()
    {
        this.dashBoardPage.accessToSection("Users","Add New");
        this.userEditPage.typePassword("Mensaje2017");
        assertEquals("#c3ff88",this.userEditPage.getStrengthIndicatorColor());
        assertEquals("Strong",this.userEditPage.getStrengthIndicatorLevel());
    }

}
