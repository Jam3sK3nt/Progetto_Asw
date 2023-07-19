package asw.goodbooks.recensioni.domain;
import asw.goodbooks.common.api.event.DomainEvent; 

public interface RecensioneDomainEventPublisher {

    public void publish(DomainEvent event);
    
}

