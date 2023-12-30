package tech.manleysoftware.blog;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path(BlogResource.BLOG_PATH)
public class BlogResource {

    public static final String BLOG_PATH = "/blog";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BlogSummariesDto getBlogSummaries() {
        List<Blog> blogs = Blog.listAll();
        List<BlogSummaryDto> blogSummaries = blogs.stream().map(BlogSummaryDto::fromEntity).toList();
        return new BlogSummariesDto(blogSummaries);
    }
}
