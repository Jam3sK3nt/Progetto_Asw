package asw.goodbooks.recensioniservice.api.event;
import asw.goodbooks.common.api.event.DomainEvent; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecensioneCreatedEvent implements DomainEvent {

    private Long id; 
    private String recensore; 
    private String titoloLibro; 
    private String autoreLibro; 
    private String testoRecensione;  
	 
	
}
