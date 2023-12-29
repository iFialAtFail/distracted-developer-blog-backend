package tech.manleysoftware.blog;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface BlogResource  extends PanacheEntityResource<Blog, Long> {
}
