package asw.goodbooks.connessioni.eventpublisher;
import asw.goodbooks.common.api.event.DomainEvent;
import asw.goodbooks.connessioniservice.api.event.ConnessioniServiceEventChannel; 
import asw.goodbooks.connessioni.domain.ConnessioneDomainEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class ConnessioneDomainEventPublisherImpl implements ConnessioneDomainEventPublisher {
    

    private final Logger logger = Logger.getLogger(ConnessioneDomainEventPublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

	private String channel = ConnessioniServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHING EVENT: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }

}
