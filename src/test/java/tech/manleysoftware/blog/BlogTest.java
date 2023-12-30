package tech.manleysoftware.blog;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@QuarkusTest
class BlogTest {

    @Test
    void shouldCreateBlogPost() {
        Blog blog = new Blog();
        ZonedDateTime postTime = ZonedDateTime.now();
        String blogTitle = "Blog Title";
        String blogContent = "Blog content string";
        String author = "Michael Manley";

        blog.setContent(blogContent);
        blog.setTitle(blogTitle);
        blog.setAuthor(author);
        blog.setDatePosted(postTime);

        assertThat(blog.getContent()).isEqualTo(blogContent);
        assertThat(blog.getTitle()).isEqualTo(blogTitle);
        assertThat(blog.getDatePosted()).isEqualTo(postTime);
        assertThat(blog.getAuthor()).isEqualTo(author);
    }

    @Test
    @TestTransaction
    void shouldSaveAndReturnBlogEntity() {
        Blog blog = new Blog();
        ZonedDateTime postTime = ZonedDateTime.now();
        String blogTitle = "Blog Title";
        String blogContent = "Blog content string";
        String author = "Michael Manley";

        blog.setContent(blogContent);
        blog.setTitle(blogTitle);
        blog.setAuthor(author);
        blog.setDatePosted(postTime);

        blog.persist();

        Blog found = Blog.findById(blog.id);

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo(blogTitle);
        assertThat(found.getDatePosted()).isEqualTo(postTime);
        assertThat(found.getAuthor()).isEqualTo(author);
        assertThat(found.getContent()).isEqualTo(blogContent);
    }

    @Test
    @TestTransaction
    void shouldThrowOnInvalidEntity() {
        Blog blog = new Blog();
        String blogTitle = "Blog Title";
        String blogContent = "Blog content string";
        String author = "Michael Manley";

        blog.setContent(blogContent);
        blog.setTitle(blogTitle);
        blog.setAuthor(author);
        blog.setDatePosted(null);

        assertThatThrownBy(blog::persistAndFlush).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @TestTransaction
    void shouldEnforceUniqueDateTimeToPreventSpam() {
        ZonedDateTime localDateTime = ZonedDateTime.now();
        Blog blog = createBasicBlog(localDateTime);
        blog.persistAndFlush();
        Blog duplicateBlog = createBasicBlog(localDateTime);
        assertThatThrownBy(duplicateBlog::persistAndFlush).isInstanceOf(PersistenceException.class);
    }

    private static Blog createBasicBlog(ZonedDateTime date) {
        Blog blog = new Blog();
        String blogTitle = "Blog Title";
        String blogContent = "Blog content string";
        String author = "Michael Manley";
        String summary = "My Summary";
        ZonedDateTime postTime = ZonedDateTime.now();


        blog.setContent(blogContent);
        blog.setTitle(blogTitle);
        blog.setAuthor(author);
        blog.setDatePosted(date != null ? date : postTime);
        blog.setSummary(summary);
        return blog;
    }

}