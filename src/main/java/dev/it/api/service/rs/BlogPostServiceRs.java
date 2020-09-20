package dev.it.api.service.rs;

import dev.it.api.common.RsRepositoryService;
import dev.it.api.model.BlogPost;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.MultiMap;

import java.util.Map;

import static dev.it.api.management.AppConstants.BLOGPOSTS_PATH;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;


@RouteBase(path = BLOGPOSTS_PATH, produces = APPLICATION_JSON)
public class BlogPostServiceRs extends RsRepositoryService<BlogPost> {

    public BlogPostServiceRs(Class<BlogPost> entityClass) {
        super(entityClass);
    }

    @Override
    public void applyRestictions(StringBuffer sb, MultiMap multiMap, Map<String, Object> parameters) {


    }
}