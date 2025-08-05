package com.app.campionsl.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String RESULT_QUEUE = "resultado-partida";

    @Bean
    public Queue matchResultQueue(){
        return new Queue(RESULT_QUEUE, true);
    }

}
