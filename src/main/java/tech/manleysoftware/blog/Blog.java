package tech.manleysoftware.blog;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

@Entity
public class Blog extends PanacheEntity {

    @Lob
    @NotBlank(message = "Content may not be blank")
    String content;

    @NotBlank(message = "Summary may not be blank")
    String summary;

    @NotBlank(message = "Title may not be blank")
    String title;

    @NotBlank(message = "Author may not be blank")
    String author;

    @Column(unique = true)
    @NotNull
    ZonedDateTime datePosted;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ZonedDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(ZonedDateTime datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        if (!content.equals(blog.content)) return false;
        if (!summary.equals(blog.summary)) return false;
        if (!title.equals(blog.title)) return false;
        if (!author.equals(blog.author)) return false;
        return datePosted.isEqual(blog.datePosted);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + summary.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + datePosted.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", datePosted=" + datePosted +
                ", id=" + id +
                '}';
    }
}
