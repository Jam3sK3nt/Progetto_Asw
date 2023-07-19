package asw.goodbooks.recensioniseguite.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class RecensioniSeguiteService {
    
    @Autowired
    private RecensioniSeguiteRepository recensioniSeguiteRepository;

    @Autowired
	private ConnessioniConAutoriRepository connessioniConAutoriRepository;

	@Autowired
	private ConnessioniConRecensoriRepository connessioniConRecensoriRepository;

	@Autowired
	private RecensioniRepository recensioniRepository;

	@Autowired 
	private ConnessioniClientPort connessioniClient;

	@Autowired 
	private RecensioniClientPort recensioniClient;

	/* Trova le recensioni seguite da un utente. */ 
	public Collection<Recensione> getRecensioniSeguite(String utente) {
		Collection<Recensione> recensioniSeguite = new TreeSet<>(); 
		
		Collection<ConnessioneConAutore> connessioniConAutore = connessioniClient.getConnessioniConAutoreByUtente(utente); 
		Collection<String> autoriSeguiti = 
			connessioniConAutore
				.stream()
				.map(c -> c.getAutore())
				.collect(Collectors.toSet()); 
		if (autoriSeguiti.size()>0) {
			Collection<Recensione> recensioniDiAutori = recensioniClient.getRecensioniByAutori(autoriSeguiti);
			recensioniSeguite.addAll(recensioniDiAutori); 
		}
		
		Collection<ConnessioneConRecensore> connessioniConRecensore = connessioniClient.getConnessioniConRecensoreByUtente(utente); 
		Collection<String> recensoriSeguiti = 
			connessioniConRecensore
				.stream()
				.map(c -> c.getRecensore())
				.collect(Collectors.toSet()); 
		if (recensoriSeguiti.size()>0) {
			Collection<Recensione> recensioniDiRecensori = recensioniClient.getRecensioniByRecensori(recensoriSeguiti);
			recensioniSeguite.addAll(recensioniDiRecensori); 
		}

		return recensioniSeguite; 
	}


	public ConnessioneConAutore createConnessioneConAutore(String utente, String autore) {
		ConnessioneConAutore connessione = new ConnessioneConAutore(utente, autore); 
		connessione = connessioniConAutoriRepository.save(connessione);
		updateRecensioniSeguiteWithConnessioneConAutore(utente, autore);
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
		updateRecensioniSeguiteWithConnessioneConRecensore(utente, recensore);
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

	
	public Recensione createRecensione(String recensore, String titoloLibro, String autoreLibro, String testoRecensione) {
		Recensione recensione = new Recensione(recensore, titoloLibro, autoreLibro, testoRecensione); 
		recensione = recensioniRepository.save(recensione);
			// Verifica se ci sono utenti che seguono l'autore
		Collection<ConnessioneConAutore> connessioniConAutore = connessioniConAutoriRepository.findByAutore(autoreLibro);
		for (ConnessioneConAutore connessione : connessioniConAutore) {
			updateRecensioniSeguiteWithConnessioneConAutore(connessione.getUtente(), autoreLibro);
		}

		// Verifica se ci sono utenti che seguono il recensore
		Collection<ConnessioneConRecensore> connessioniConRecensore = connessioniConRecensoriRepository.findByRecensore(recensore);
		for (ConnessioneConRecensore connessione : connessioniConRecensore) {
			updateRecensioniSeguiteWithConnessioneConRecensore(connessione.getUtente(), recensore);
		}
		return recensione;
	}

 	public Recensione getRecensione(Long id) {
		Recensione recensione = recensioniRepository.findById(id).orElse(null);
		return recensione;
	}

	public Collection<Recensione> getRecensioni() {
		Collection<Recensione> recensioni = recensioniRepository.findAll();
		return recensioni;
	}

	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		return recensioni;
	}

	public Collection<Recensione> getRecensioniByRecensori(Collection<String> recensori) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensoreIn(recensori);
		return recensioni;
	}

	public Collection<Recensione> getRecensioniByTitolo(String titolo) {
		Collection<Recensione> recensioni = recensioniRepository.findByTitoloLibro(titolo);
		return recensioni;
	}

	public Collection<Recensione> getRecensioniByAutore(String autore) {
		Collection<Recensione> recensioni = recensioniRepository.findByAutoreLibro(autore);
		return recensioni;
	}

	public Collection<Recensione> getRecensioniByAutori(Collection<String> autori) {
		Collection<Recensione> recensioni = recensioniRepository.findByAutoreLibroIn(autori);
		return recensioni;
	}
	private void updateRecensioniSeguiteWithConnessioneConAutore(String utente, String autoreLibro) {
		Collection<Recensione> recensioni = recensioniRepository.findByAutoreLibro(autoreLibro);
		Collection<Long> idRecensioni = recensioni.stream().map(Recensione::getId).collect(Collectors.toList());
	
		for (Long idRecensione : idRecensioni) {
			if (!recensioniSeguiteRepository.existsByUtenteAndIdRecensione(utente, idRecensione)) {
				Recensione recensione = recensioni.stream().filter(r -> r.getId().equals(idRecensione)).findFirst().orElse(null);
				if (recensione != null) {
					RecensioniSeguite recensioneSeguita = new RecensioniSeguite(utente, idRecensione, recensione.getRecensore(), recensione.getTitoloLibro(), autoreLibro, recensione.getTestoRecensione());
					recensioniSeguiteRepository.save(recensioneSeguita);
				}
			}
		}
	}
	
	private void updateRecensioniSeguiteWithConnessioneConRecensore(String utente, String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		Collection<Long> idRecensioni = recensioni.stream().map(Recensione::getId).collect(Collectors.toList());
	
		for (Long idRecensione : idRecensioni) {
			if (!recensioniSeguiteRepository.existsByUtenteAndIdRecensione(utente, idRecensione)) {
				Recensione recensione = recensioni.stream().filter(r -> r.getId().equals(idRecensione)).findFirst().orElse(null);
				if (recensione != null) {
					RecensioniSeguite recensioneSeguita = new RecensioniSeguite(utente, idRecensione, recensore, recensione.getTitoloLibro(), recensione.getAutoreLibro(), recensione.getTestoRecensione());
					recensioniSeguiteRepository.save(recensioneSeguita);
				}
			}
		}
	}
	public Collection<RecensioniSeguite> getRecensioniSeguiteByUtente(String utente) {
        return recensioniSeguiteRepository.findByUtente(utente);
    }
	

	

	
	
	
	
	
}






