
package de.fshahy.fili.catalog;


import io.helidon.logging.common.LogConfig;
import de.fshahy.fili.catalog.services.ProductCategoryService;
import de.fshahy.fili.catalog.services.ProductService;
import io.helidon.common.context.Contexts;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;


public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        
        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Config.create();
        Config.global(config);

        DbClient dbClient = DbClient.create(config.get("db"));
        Contexts.globalContext().register(dbClient);


        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port());
    }

    static void routing(HttpRouting.Builder routing) {
        routing
               .register("/api/product_categories", new ProductCategoryService())
               .register("/api/products", new ProductService());
    }
    
}