package asw.goodbooks.recensioni.eventpublisher;
import asw.goodbooks.common.api.event.DomainEvent;
import asw.goodbooks.recensioniservice.api.event.RecensioniServiceEventChannel; 
import asw.goodbooks.recensioni.domain.RecensioneDomainEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RecensioneDomainEventPublisherImpl implements RecensioneDomainEventPublisher {
    

    private final Logger logger = Logger.getLogger(RecensioneDomainEventPublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

	private String channel = RecensioniServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHING EVENT: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }

}
