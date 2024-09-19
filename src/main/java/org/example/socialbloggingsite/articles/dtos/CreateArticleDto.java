package org.example.socialbloggingsite.articles.dtos;

public class CreateArticleDto {
    private String title;
    private String content;
    private String published;
    private String category;
    private int user_id;

    public String getTitle() {
        return title;
    }

    public CreateArticleDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CreateArticleDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPublished() {
        return published;
    }

    public CreateArticleDto setPublished(String published) {
        this.published = published;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CreateArticleDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public CreateArticleDto setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }
}
