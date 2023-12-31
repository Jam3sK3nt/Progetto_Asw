package asw.goodbooks.recensioniseguite.rest;

import asw.goodbooks.recensioniseguite.domain.*; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant; 
import java.time.Duration; 

import java.util.*; 

import java.util.logging.Logger; 

@RestController
public class RecensioniSeguiteController {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
	private RecensioniSeguiteService recensioniSeguiteService;

	/* Trova le recensioni (in formato breve) degli utenti seguiti da utente. */ 
	//@GetMapping("/recensioniseguite/{utente}")
	// public Collection<Recensione> getRecensioniSeguite(@PathVariable String utente) {
	// 	Instant start = Instant.now();
	// 	logger.info("REST CALL: getRecensioniSeguite " + utente); 
	// 	Collection<Recensione> recensioni = recensioniSeguiteService.getRecensioniSeguite(utente); 
	// 	Duration duration = Duration.between(start, Instant.now()); 
	// 	logger.info("getRecensioniSeguite " + utente + " (trovate " + recensioni.size() + " recensioni in " + duration.toMillis() + " ms): " + recensioni);
	// 	return recensioni; 
	// }
	@GetMapping("/recensioniseguite/{utente}")
	public Collection<RecensioniSeguite> getRecensioniSeguiteByUtente(@PathVariable String utente) {
        Instant start = Instant.now();
        logger.info("REST CALL: getRecensioniSeguiteByUtente " + utente); 
        Collection<RecensioniSeguite> recensioniSeguite = recensioniSeguiteService.getRecensioniSeguiteByUtente(utente); 
        Duration duration = Duration.between(start, Instant.now()); 
        logger.info("getRecensioniSeguiteByUtente " + utente + " (trovate " + recensioniSeguite.size() + " recensioni in " + duration.toMillis() + " ms): " + recensioniSeguite);
        return recensioniSeguite; 
    }
	
}
