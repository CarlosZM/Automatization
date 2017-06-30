package test.entity;

/**
 * Created by carlos on 6/30/17.
 */
public class LinkCategory {

    private String name;

    private String slug;

    private String description;

    public LinkCategory(String name, String slug, String description) {
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }
}

