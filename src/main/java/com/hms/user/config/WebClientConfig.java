package com.hms.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

        @Bean
        public WebClient.Builder getWebClienBuilder(){
            return WebClient.builder().defaultHeader("X-SECRET-KEY", "SECRET").filter(logRequest());
        }

        private ExchangeFilterFunction logRequest(){
            return ExchangeFilterFunction.ofRequestProcessor(clientRequest->{
                System.out.println("Request : "+clientRequest.method()+ ":  URL: "+clientRequest.url());
                return Mono.just(clientRequest);
            });
        }
}
