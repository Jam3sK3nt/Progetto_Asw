package asw.goodbooks.recensioniseguite.domain;
import asw.goodbooks.common.api.event.DomainEvent;
import asw.goodbooks.connessioniservice.api.event.*;
import asw.goodbooks.recensioniservice.api.event.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class DomainEventConsumer {

	private final Logger logger = Logger.getLogger(DomainEventConsumer.class.toString());
	
	@Autowired
	private RecensioniSeguiteService recensioniSeguiteService;

	public void onEvent(DomainEvent event) {
        if (event.getClass().equals(ConnessioneConAutoreCreatedEvent.class)) {
			ConnessioneConAutoreCreatedEvent ccae = (ConnessioneConAutoreCreatedEvent) event;
			connessioneConAutoreCreated(ccae); 
		}else if (event.getClass().equals(ConnessioneConRecensoreCreatedEvent.class)) {
			ConnessioneConRecensoreCreatedEvent ccre = (ConnessioneConRecensoreCreatedEvent) event;
			connessioneConRecensoreCreated(ccre); 
		}else if (event.getClass().equals(RecensioneCreatedEvent.class)) {
			RecensioneCreatedEvent rce = (RecensioneCreatedEvent) event;
			recensioneCreated(rce); 
		}else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	
    private void connessioneConAutoreCreated(ConnessioneConAutoreCreatedEvent event) {
		ConnessioneConAutore connessioneConAutore = new ConnessioneConAutore(event.getUtente(), event.getAutore());
		recensioniSeguiteService.createConnessioneConAutore(event.getUtente(), event.getAutore());
		logger.info("CREATED  CONNESSIONE CON AUTORE: " + connessioneConAutore);
	}
	private void connessioneConRecensoreCreated(ConnessioneConRecensoreCreatedEvent event) {
		ConnessioneConRecensore connessioneConRecensore = new ConnessioneConRecensore(event.getUtente(), event.getRecensore());
		recensioniSeguiteService.createConnessioneConRecensore(event.getUtente(), event.getRecensore());
		logger.info("CREATED  CONNESSIONE CON RECENSORE: " + connessioneConRecensore);
	}
	
    private void recensioneCreated(RecensioneCreatedEvent event) {
		Recensione recensione = new Recensione(event.getRecensore(), event.getTitoloLibro(), event.getAutoreLibro(), event.getTestoRecensione());
		recensioniSeguiteService.createRecensione(event.getRecensore(), event.getTitoloLibro(), event.getAutoreLibro(), event.getTestoRecensione());
		logger.info("CREATED  RECENSIONE: " + recensione);
	}

	
}

























