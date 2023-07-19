package asw.goodbooks.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface RecensioniSeguiteRepository extends CrudRepository<RecensioniSeguite, Long> {


    Collection<RecensioniSeguite> findAll();
    Collection<RecensioniSeguite> findByRecensore(String recensore);
    Collection<RecensioniSeguite> findByTitoloLibro(String titoloLibro);
    Collection<RecensioniSeguite> findByAutoreLibro(String autoreLibro);
    boolean existsByUtenteAndIdRecensione(String utente, Long idRecensione);
	Collection<RecensioniSeguite> findByUtente(String utente);

}

