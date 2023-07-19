package asw.goodbooks.connessioni.domain;
import asw.goodbooks.common.api.event.DomainEvent; 
import asw.goodbooks.connessioniservice.api.event.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*; 

@Service
public class ConnessioniService {

	@Autowired
	private ConnessioniConAutoriRepository connessioniConAutoriRepository;

	@Autowired
	private ConnessioniConRecensoriRepository connessioniConRecensoriRepository;

	@Autowired
    private ConnessioneDomainEventPublisher domainEventPublisher;

 	public ConnessioneConAutore createConnessioneConAutore(String utente, String autore) {
		ConnessioneConAutore connessione = new ConnessioneConAutore(utente, autore); 
		connessione = connessioniConAutoriRepository.save(connessione);
		DomainEvent event = new ConnessioneConAutoreCreatedEvent(connessione.getId(), connessione.getUtente(), connessione.getAutore());
		domainEventPublisher.publish(event);
		return connessione;
	}

 	public ConnessioneConAutore getConnessioneConAutore(Long id) {
		ConnessioneConAutore connessione = connessioniConAutoriRepository.findById(id).orElse(null);
		return connessione;
	}

 	public Collection<ConnessioneConAutore> getConnessioniConAutore() {
		Collection<ConnessioneConAutore> connessioni = connessioniConAutoriRepository.findAll();
		return connessioni;
	}

	public Collection<ConnessioneConAutore> getConnessioniConAutoreByUtente(String utente) {
		Collection<ConnessioneConAutore> connessioni = connessioniConAutoriRepository.findByUtente(utente);
		return connessioni;
	}

 	public ConnessioneConRecensore createConnessioneConRecensore(String utente, String recensore) {
		ConnessioneConRecensore connessione = new ConnessioneConRecensore(utente, recensore); 
		connessione = connessioniConRecensoriRepository.save(connessione);
		DomainEvent event = new ConnessioneConRecensoreCreatedEvent(connessione.getId(), connessione.getUtente(), connessione.getRecensore());
		domainEventPublisher.publish(event);
		return connessione;
	}

 	public ConnessioneConRecensore getConnessioneConRecensore(Long id) {
		ConnessioneConRecensore connessione = connessioniConRecensoriRepository.findById(id).orElse(null);
		return connessione;
	}

 	public Collection<ConnessioneConRecensore> getConnessioniConRecensore() {
		Collection<ConnessioneConRecensore> connessioni = connessioniConRecensoriRepository.findAll();
		return connessioni;
	}

	public Collection<ConnessioneConRecensore> getConnessioniConRecensoreByUtente(String utente) {
		Collection<ConnessioneConRecensore> connessioni = connessioniConRecensoriRepository.findByUtente(utente);
		return connessioni;
	}

}
