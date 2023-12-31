package tech.manleysoftware.blog;

import java.time.ZonedDateTime;
import java.util.Objects;

public class BlogDto {

    Long id;
    String title;
    String content;
    String summary;
    String author;
    ZonedDateTime postDate;

    public BlogDto(Long id, String title, String content, String summary, String author, ZonedDateTime postDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.author = author;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlogDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ZonedDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(ZonedDateTime postDate) {
        this.postDate = postDate;
    }

    public static BlogDto fromEntity(Blog blog) {
        return new BlogDto(
                blog.id,
                blog.getTitle(),
                blog.getContent(),
                blog.getSummary(),
                blog.getAuthor(),
                blog.getDatePosted()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogDto blogDto = (BlogDto) o;

        if (!Objects.equals(title, blogDto.title)) return false;
        if (!Objects.equals(content, blogDto.content)) return false;
        if (!Objects.equals(summary, blogDto.summary)) return false;
        if (!Objects.equals(author, blogDto.author)) return false;
        return postDate.isEqual(blogDto.postDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlogDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", author='" + author + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
