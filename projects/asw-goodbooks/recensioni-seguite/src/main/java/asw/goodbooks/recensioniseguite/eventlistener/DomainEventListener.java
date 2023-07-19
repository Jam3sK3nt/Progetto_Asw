package asw.goodbooks.recensioniseguite.eventlistener;
import asw.goodbooks.common.api.event.DomainEvent; 
import asw.goodbooks.connessioniservice.api.event.*;
import asw.goodbooks.recensioniservice.api.event.*;

import asw.goodbooks.recensioniseguite.domain.DomainEventConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;


@Component 
public class DomainEventListener {
    

    private final Logger logger = Logger.getLogger(DomainEventListener.class.toString());

    @Autowired
    private DomainEventConsumer domainEventConsumer;

    
    @KafkaListener(topics = {ConnessioniServiceEventChannel.channel, RecensioniServiceEventChannel.channel})
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
		domainEventConsumer.onEvent(event); 
    }

}





