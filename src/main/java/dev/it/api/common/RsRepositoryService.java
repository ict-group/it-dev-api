package dev.it.api.common;

import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.smallrye.mutiny.Uni;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.*;

import static io.quarkus.vertx.web.Route.HandlerType.FAILURE;
import static io.vertx.core.http.HttpMethod.GET;

public abstract class RsRepositoryService<T> {

    private static final long serialVersionUID = 1L;

    protected Logger logger = Logger.getLogger(getClass());

    private Class<T> entityClass;

    @Inject
    Mutiny.Session session;

    public Mutiny.Session getSession() {
        return session;
    }

    public void setSession(Mutiny.Session session) {
        this.session = session;
    }

    public RsRepositoryService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public RsRepositoryService() {
    }

    @Route(methods = GET, path = "/:id")
    public Uni<T> fetch(@Param Optional<String> id) {
        logger.info("fetch: " + id);
        return session.find(this.entityClass, id);
    }

    public abstract void applyRestictions(StringBuffer sb, MultiMap multiMap, Map<String, Object> parameters);

    public Mutiny.Query getSearch(Optional<String> orderBy, boolean count, MultiMap multiMap) {
        StringBuffer sb = new StringBuffer();
        Map<String, Object> parameters = new HashMap<>();
        applyRestictions(sb, multiMap, parameters);
        if (count) {
            createCountQuery(sb.toString(), 0);
        } else {
            createFindQuery(sb.toString(), 0);
        }
        Mutiny.Query query = session.createQuery("");
        return query;
    }

    @Route(methods = GET, path = "/")
    public Uni<List<T>> getList(
            @Param("startRow") Optional<String> startRow,
            @Param("pageSize") Optional<String> pageSize,
            @Param("orderBy") Optional<String> orderBy,
            RoutingExchange routingExchange) {
        logger.info("getList");
        Integer _startRow = startRow.map(Integer::valueOf).orElse(0);
        Integer _pageSize = pageSize.map(Integer::valueOf).orElse(10);
        Mutiny.Query search = getSearch(orderBy, false, routingExchange.request().params());
        Integer _listSize = getSearch(orderBy, true, routingExchange.request().params()).getFirstResult();
        Uni<List<T>> list;
        if (_listSize == 0) {
            list = Uni.createFrom().item(new ArrayList<>());
        } else {
            list = search.setFirstResult(_startRow).setMaxResults(_pageSize).getResultList();
        }
        routingExchange.response()
                .setStatusCode(200)
                .putHeader("Access-Control-Expose-Headers", "startRow, pageSize, listSize")
                .putHeader("startRow", _startRow.toString())
                .putHeader("pageSize", _pageSize.toString())
                .putHeader("listSize", _listSize.toString());
        return list;
    }

    @Route(path = "/*", type = FAILURE)
    public void error(RoutingContext context) {
        Throwable t = context.failure();
        if (t != null) {
            logger.error("Failed to handle request", t);
            int status = context.statusCode();
            String chunk = "";
            if (t instanceof NoSuchElementException) {
                status = 404;
            } else if (t instanceof IllegalArgumentException) {
                status = 422;
                chunk = new JsonObject().put("code", status)
                        .put("exceptionType", t.getClass().getName()).put("error", t.getMessage()).encode();
            }
            context.response().setStatusCode(status).end(chunk);
        } else {
            // Continue with the default error handler
            context.next();
        }
    }

    protected String createFindQuery(String query, int paramCount) {
        if (query == null) {
            return "FROM " + getEntityName();
        }

        String trimmed = query.trim();
        if (trimmed.isEmpty()) {
            return "FROM " + getEntityName();
        }

        if (isNamedQuery(query)) {
            // we return named query as is
            return query;
        }

        String trimmedLc = trimmed.toLowerCase();
        if (trimmedLc.startsWith("from ") || trimmedLc.startsWith("select ")) {
            return query;
        }
        if (trimmedLc.startsWith("order by ")) {
            return "FROM " + getEntityName() + " " + query;
        }
        if (trimmedLc.indexOf(' ') == -1 && trimmedLc.indexOf('=') == -1 && paramCount == 1) {
            query += " = ?1";
        }
        return "FROM " + getEntityName() + " WHERE " + query;
    }

    protected boolean isNamedQuery(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        return query.charAt(0) == '#';
    }

    protected String createCountQuery(String query, int paramCount) {
        if (query == null)
            return "SELECT COUNT(*) FROM " + getEntityName();

        String trimmed = query.trim();
        if (trimmed.isEmpty())
            return "SELECT COUNT(*) FROM " + getEntityName();

        String trimmedLc = trimmed.toLowerCase();
        if (trimmedLc.startsWith("from ")) {
            return "SELECT COUNT(*) " + query;
        }
        if (trimmedLc.startsWith("order by ")) {
            // ignore it
            return "SELECT COUNT(*) FROM " + getEntityName();
        }
        if (trimmedLc.indexOf(' ') == -1 && trimmedLc.indexOf('=') == -1 && paramCount == 1) {
            query += " = ?1";
        }
        return "SELECT COUNT(*) FROM " + getEntityName() + " WHERE " + query;
    }

    private String getEntityName() {
        return entityClass.getName();
    }

}
