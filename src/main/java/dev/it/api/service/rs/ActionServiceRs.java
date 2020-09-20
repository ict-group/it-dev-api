package dev.it.api.service.rs;

import dev.it.api.common.RsRepositoryService;
import dev.it.api.model.Action;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.MultiMap;

import java.util.Map;

import static dev.it.api.management.AppConstants.ACTIONS_PATH;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;


@RouteBase(path = ACTIONS_PATH, produces = APPLICATION_JSON)
public class ActionServiceRs extends RsRepositoryService<Action> {

    @Override
    public void applyRestictions(StringBuffer sb, MultiMap multiMap, Map<String, Object> parameters) {

    }
}