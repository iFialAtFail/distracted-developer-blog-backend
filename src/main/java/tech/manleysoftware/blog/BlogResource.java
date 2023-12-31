package tech.manleysoftware.blog;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public RestResponse<BlogDto> postNewBlogPost(BlogDto blogDto, @Context UriInfo uriInfo) {
        Blog blog = new Blog();
        blog.setSummary(blogDto.getSummary());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(blogDto.getAuthor());
        blog.setDatePosted(blogDto.getPostDate());
        blog.setTitle(blogDto.getTitle());

        blog.persistAndFlush();

        return RestResponse.ResponseBuilder.create(RestResponse.Status.CREATED, BlogDto.fromEntity(blog)).build();
//        return RestResponse.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(blog.id)).build());
//        return Response.created(BlogDto.fromEntity(blog)).build();
    }
}
