package test.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by carlos on 6/29/17.
 */
public class Post {

    private String name;

    private String content;

    private ArrayList<Category> categories;


    public Post(String name, String content, ArrayList<Category> categories) {
        this.name = name;
        this.content = content;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<String> categoriesNames()
    {
        ArrayList<String> categoryNames=new ArrayList<String>();
        for(Category category:this.categories)
        {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
