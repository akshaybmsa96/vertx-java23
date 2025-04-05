package com.akki;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;

public class AppModule extends AbstractModule {
    private final Vertx vertx;

    public AppModule(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(vertx);
        bind(ApiRouter.class);
    }
}