package com.akki;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import lombok.Getter;

@Getter
public class ApiRouter {
    private final Router router;

    @Inject
    public ApiRouter(Vertx vertx) {
        this.router = Router.router(vertx);

        router.get("/").handler(ctx -> {
            ctx.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x with Guice!");
        });

        router.get("/health").handler(ctx -> {
            ctx.response()
                    .putHeader("content-type", "application/json")
                    .end("{\"status\": \"UP\"}");
        });
    }

}
