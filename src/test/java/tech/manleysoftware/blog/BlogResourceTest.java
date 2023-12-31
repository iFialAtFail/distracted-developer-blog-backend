package tech.manleysoftware.blog;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BlogResourceTest implements WithAssertions {

    @Transactional
    @BeforeEach
    @AfterEach
    void setup() {
        Blog.deleteAll();
    }

    @Test
    void shouldReturnBlogs() {
        Blog blog = createAndPersistBlogInTransaction();
        BlogSummariesDto response = given()
                .when().get(BlogResource.BLOG_PATH)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().as(BlogSummariesDto.class);

        assertThat(response).isNotNull();
        assertThat(response.summaries()).containsExactly(BlogSummaryDto.fromEntity(blog));
    }

    @Test
    void shouldReturnNoBlogs() {
        BlogSummariesDto response = given()
                .when().get(BlogResource.BLOG_PATH)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().as(BlogSummariesDto.class);

        assertThat(response).isNotNull();
        assertThat(response.summaries()).isEmpty();
    }

    @Test
    void shouldReturnCreatedBlog() {
        Blog blog = createBlog();
        BlogDto requestBody = BlogDto.fromEntity(blog);
        BlogDto response = given()
                .when()
                    .body(requestBody)
                    .contentType(ContentType.JSON)
                    .post(BlogResource.BLOG_PATH)
                .then()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .extract().as(BlogDto.class);

        assertThat(response).isNotNull();

        BlogDto expected = BlogDto.fromEntity(blog);
        assertThat(response).isEqualTo(expected);
    }

    Blog createBlog() {
        Blog blog = new Blog();
        blog.setAuthor("Mike");
        LocalDateTime localDateTime = LocalDateTime.of(2020, Month.JANUARY, 26, 4, 4);
        ZonedDateTime datePosted = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Paris"));
        blog.setDatePosted(datePosted);
        blog.setContent("This is my large LOB content");
        blog.setTitle("Title");
        blog.setSummary("This is the summary to be displayed on the blog cards");

        return blog;
    }

    @Transactional
    Blog createAndPersistBlogInTransaction() {
        Blog blog = createBlog();
        blog.persistAndFlush();
        return blog;
    }
}