package de.fshahy.fili.catalog.services;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Map;

import io.helidon.common.context.Contexts;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbExecute;
import io.helidon.dbclient.DbTransaction;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

public class ProductCategoryService implements HttpService {

    private static final Logger LOGGER = System.getLogger(ProductCategoryService.class.getName());
    private static final JsonBuilderFactory JSON_FACTORY = Json.createBuilderFactory(Map.of());

    private final DbClient dbClient;

    public ProductCategoryService() {
        Config config = Config.global().get("db");
        this.dbClient = Contexts.globalContext()
                .get(DbClient.class)
                .orElseGet(() -> DbClient.create(config));
    }

    private void listAll(ServerRequest request, ServerResponse response) {
        JsonArray jsonArray = dbClient.execute()
                    .namedQuery("select-all-product-categories")
                    .map(row -> row.as(JsonObject.class))
                    .collect(JSON_FACTORY::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::addAll)
                    .build();
        response.send(jsonArray);
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/", this::listAll);
    }
    
}
