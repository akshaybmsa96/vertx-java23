package com.akki;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class App extends AbstractVerticle {


    @Override
    public void start(Promise<Void> startPromise) {

        Injector injector = Guice.createInjector(new AppModule(vertx));
        ApiRouter apiRouter = injector.getInstance(ApiRouter.class);
        Router router = apiRouter.getRouter();
        System.out.println("Server starting on port 8080 ...");

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080)
                .onSuccess(server -> {
                    System.out.println("Server started on port " + server.actualPort());
                    startPromise.complete();
                })
                .onFailure(err-> {
                    System.err.println("Failed to start server: " + err.getMessage());
                    startPromise.fail(err);
                });
    }

    public static void main(String[] args) {
        Test test = Guice.createInjector(new AppModule(Vertx.vertx())).getInstance(Test.class);
        System.out.println(Pojo.builder().msg("Hello World!").build());
        test.test();

        Vertx vertx = Vertx.vertx();
        System.out.println("Starting App deployment...");
        vertx.deployVerticle(new App(), res -> {
            if (res.succeeded()) {
                System.out.println("App deployed successfully");
            } else {
                System.err.println("Deployment failed: " + res.cause());
            }
        });

        // Prevent app from exiting
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
