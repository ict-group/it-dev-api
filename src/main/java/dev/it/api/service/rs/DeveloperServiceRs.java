package dev.it.api.service.rs;

import dev.it.api.common.RsRepositoryService;
import dev.it.api.model.Developer;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.MultiMap;

import java.util.Map;

import static dev.it.api.management.AppConstants.DEVELOPERS_PATH;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;


@RouteBase(path = DEVELOPERS_PATH, produces = APPLICATION_JSON)
public class DeveloperServiceRs extends RsRepositoryService<Developer> {

    public DeveloperServiceRs(Class<Developer> entityClass) {
        super(entityClass);
    }

    @Override
    public void applyRestictions(StringBuffer sb, MultiMap multiMap, Map<String, Object> parameters) {


    }
}