package asw.goodbooks.connessioniservice.api.event;

import asw.goodbooks.common.api.event.DomainEvent; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnessioneConAutoreCreatedEvent implements DomainEvent {

	private Long id; 
	private String Utente; 
	private String Autore; 
	
}
