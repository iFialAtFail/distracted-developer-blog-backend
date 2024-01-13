package tech.manleysoftware.blog;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

@Singleton
public class InitTestData {

    @Transactional
    public void loadBlogTestData(@Observes StartupEvent startupEvent) {
        for (int i = 0; i < 7; i++) {
            Blog blog = createBlog(i);
            blog.persist();
        }
    }

    @NotNull Blog createBlog(int i) {
        Blog blog = new Blog();
        blog.setTitle("Title " + i);
        blog.setAuthor("Mike M. ");
        blog.setContent("This is my super long running content that definitely needs to be in a CLOB...");
        blog.setSummary("Summary number " + i);
        blog.setDatePosted(ZonedDateTime.now());
        return blog;
    }
}
