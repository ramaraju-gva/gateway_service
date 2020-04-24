package com.raju.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            r ->
                r.path("/posts/**")
                    .filters(
                        f ->
                            f.rewritePath("/posts/(?<segment>.*)", "/posts/${segment}")
                                .hystrix(c -> c.setName("RapidAPI:Words")))
                    .uri("https://jsonplaceholder.typicode.com")
                    .id("posts-service"))
        .build();
  }
}
