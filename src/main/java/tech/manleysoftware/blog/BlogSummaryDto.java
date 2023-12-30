package tech.manleysoftware.blog;

import java.time.ZonedDateTime;

public class BlogSummaryDto {

    Long id;
    String title;
    String summary;
    String author;
    ZonedDateTime postDate;

    public BlogSummaryDto(Long id, String title, String summary, String author, ZonedDateTime postDate) {
        this.id = id;
        this.title = title;
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

    public BlogSummaryDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static BlogSummaryDto fromEntity(Blog blog) {
        return new BlogSummaryDto(
                blog.id,
                blog.getTitle(),
                blog.getSummary(),
                blog.getAuthor(),
                blog.getDatePosted()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogSummaryDto that = (BlogSummaryDto) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!summary.equals(that.summary)) return false;
        if (!author.equals(that.author)) return false;
        return postDate.isEqual(that.postDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + summary.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + postDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlogSummaryDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", author='" + author + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
