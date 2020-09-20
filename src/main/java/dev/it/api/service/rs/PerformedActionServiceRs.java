package dev.it.api.service.rs;

import dev.it.api.common.RsRepositoryService;
import dev.it.api.model.PerformedAction;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.MultiMap;

import java.util.Map;

import static dev.it.api.management.AppConstants.PERFORMED_ACTIONS_PATH;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;


@RouteBase(path = PERFORMED_ACTIONS_PATH, produces = APPLICATION_JSON)
public class PerformedActionServiceRs extends RsRepositoryService<PerformedAction> {

    public PerformedActionServiceRs(Class<PerformedAction> entityClass) {
        super(entityClass);
    }

    @Override
    public void applyRestictions(StringBuffer sb, MultiMap multiMap, Map<String, Object> parameters) {


    }
}