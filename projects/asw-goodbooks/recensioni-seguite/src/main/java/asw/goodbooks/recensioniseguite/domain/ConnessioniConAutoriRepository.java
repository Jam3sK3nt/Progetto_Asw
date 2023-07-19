package asw.goodbooks.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniConAutoriRepository extends CrudRepository<ConnessioneConAutore, Long> {

	public Collection<ConnessioneConAutore> findAll();

	public Collection<ConnessioneConAutore> findByUtente(String utente);

	public Collection<ConnessioneConAutore> findByAutore(String autore);

}

