package test;

import com.gargoylesoftware.htmlunit.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import test.entity.Category;
import test.entity.Post;
import test.entity.User;
import test.objectpage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by carlos on 6/24/17.
 */
public class PostTestCase {

    private WebDriver webDriver;

    private String path;

    private LoginObjectPage loginObjectPage;

    private DashBoardPage dashBoardPage;

    private CategoriesObjectPage categoryPage;

    private PostEditionObjectPage postEditionPage;

    private PostPreviewObjectPage previewPage;

    private PostListObjectPage postListPage;

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
        this.categoryPage=PageFactory.initElements(this.webDriver,CategoriesObjectPage.class);
        this.postEditionPage=PageFactory.initElements(this.webDriver,PostEditionObjectPage.class);
        this.webDriver.get(this.path);
        this.loginObjectPage.authenticateUser(this.user);
        this.previewPage= PageFactory.initElements(this.webDriver, PostPreviewObjectPage.class);
        this.postListPage=PageFactory.initElements(this.webDriver,PostListObjectPage.class);
    }

    /**
     * CODE:WPS_P_01
     */
    @Test
    public void testCreateValidCategory()
    {
        Category category=new Category("hola","hola","hola");
        this.dashBoardPage.accessToSection("Posts","Categories");
        String amountCategoriesPreviousCreation=this.categoryPage.getAmountCategories();
        this.categoryPage.createCategory(category);
        assertEquals(this.categoryPage.getAmountCategories(),amountCategoriesPreviousCreation);
        assertEquals(category.getName(),this.categoryPage.getName());
        assertEquals(category.getSlug(),this.categoryPage.getSlug());
        assertEquals(category.getDescription(),this.categoryPage.getDescription());
        assertEquals(0,this.categoryPage.getPosts());
    }
    /**
     * CODE:WPS_P_02
     */
    @Test
    public void testCreateValidDraftPost()
    {
        this.dashBoardPage.accessToSection("Posts","Add New");
        ArrayList<Category> categories=new ArrayList<Category>(){{
            add(new Category("hola","hola","Hola"));
        }};
        Post post=new Post("Title","Content",categories);
        this.postEditionPage.createDraftPost(post);
        assertEquals("#ffffe0",this.postEditionPage.getMessageColor());
        assertEquals("Post draft updated. Preview post",this.postEditionPage.getMessage());
        this.postEditionPage.showDraftPreview();
        assertEquals(post.getName(),this.previewPage.getTitle());
        assertEquals(post.getContent(),this.previewPage.getContent());
        assertEquals(user.getUserName(),this.previewPage.getUserName());
        assertArrayEquals(post.categoriesNames().toArray(),this.previewPage.getCategoriesNames());
        this.postEditionPage.returnDefaultControl();
    }

    /**
     * CODE:WPS_P_03
     */
    @Test
    public void testDeleteValidDraftPost()
    {
        String  titlePostToDelete="Title";
        this.dashBoardPage.accessToSection("Posts","All Posts");
        this.postListPage.showPostsType("Draft");
        int amountDeleted=this.postListPage.selectPostByTitle(titlePostToDelete);
        int amountPostsPreviousDelete=this.postListPage.getAmountByCategory("All");
        int amountPublishedPostsPreviousDelete=this.postListPage.getAmountByCategory("Published");
        int amountDraftPostsPreviousDelete=this.postListPage.getAmountByCategory("Draft");
        int amountTrashPostsPreviousDelete=this.postListPage.getAmountByCategory("Trash");
        this.postListPage.deleteSelectedPosts();
        assertEquals(amountPostsPreviousDelete-amountDeleted,this.postListPage.getAmountByCategory("All"));
        assertEquals(amountPublishedPostsPreviousDelete,this.postListPage.getAmountByCategory("Published"));
        assertEquals(amountDraftPostsPreviousDelete-amountDeleted,this.postListPage.getAmountByCategory("Draft"));
        assertEquals(amountTrashPostsPreviousDelete+amountDeleted,this.postListPage.getAmountByCategory("Trash"));
    }


    /**
     * CODE:WPS_P_04
     */
    @Test
    public void testDeleteValidCategory()
    {
        this.dashBoardPage.accessToSection("Posts","Categories");
        ArrayList<Category> categories=new ArrayList<Category>(){{
            add(new Category("hola","hola","Hola"));
        }};
        this.categoryPage.selectRecordsByName(categories.get(0).getName());

        this.categoryPage.deleteSelectedCategories();
        assertEquals("Items deleted.", this.categoryPage.getMessage());
        assertEquals("#ffffe0", this.categoryPage.getMessageColor());
        assertFalse(this.categoryPage.getCategoriesNames().contains(categories.get(0).getName()));
    }

    @After
    public void tearDown() throws Exception {
        this.webDriver.quit();
    }
}
