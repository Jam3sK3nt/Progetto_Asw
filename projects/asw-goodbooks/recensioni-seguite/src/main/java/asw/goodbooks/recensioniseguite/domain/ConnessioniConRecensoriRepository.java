package asw.goodbooks.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniConRecensoriRepository extends CrudRepository<ConnessioneConRecensore, Long> {

	public Collection<ConnessioneConRecensore> findAll();

	public Collection<ConnessioneConRecensore> findByUtente(String utente);

	public Collection<ConnessioneConRecensore> findByRecensore(String recensore);

}

