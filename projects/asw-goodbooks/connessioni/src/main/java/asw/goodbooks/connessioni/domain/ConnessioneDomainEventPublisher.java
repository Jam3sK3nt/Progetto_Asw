package asw.goodbooks.connessioni.domain;

import asw.goodbooks.common.api.event.DomainEvent; 

public interface ConnessioneDomainEventPublisher {

    public void publish(DomainEvent event);

}
