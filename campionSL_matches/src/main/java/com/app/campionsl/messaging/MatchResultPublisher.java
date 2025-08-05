package com.app.campionsl.messaging;

import com.app.campionsl.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MatchResultPublisher {

    private final RabbitTemplate rabbitTemplate;

    public MatchResultPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMatchResult(Long matchId){
        rabbitTemplate.convertAndSend(RabbitConfig.RESULT_QUEUE, matchId.toString());
    }

}
