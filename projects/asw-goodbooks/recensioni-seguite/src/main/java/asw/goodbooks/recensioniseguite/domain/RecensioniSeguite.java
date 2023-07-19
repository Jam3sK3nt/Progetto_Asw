package asw.goodbooks.recensioniseguite.domain;

import javax.persistence.*; 
import lombok.*; 

@Entity 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RecensioniSeguite implements Comparable<RecensioniSeguite> {

    @Id 
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String utente;
    private Long idRecensione;
    private String recensore;
    private String titoloLibro;
    private String autoreLibro;
    private String testoRecensione;

    public RecensioniSeguite(String utente, Long idRecensione, String recensore, String titoloLibro, String autoreLibro, String testoRecensione) {
        this();
        this.utente = utente; 
        this.idRecensione = idRecensione;
        this.recensore = recensore;
        this.titoloLibro = titoloLibro; 
        this.autoreLibro = autoreLibro; 
        this.testoRecensione = testoRecensione;
    }

    @Override
    public int compareTo(RecensioniSeguite other) {
        return this.id.compareTo(other.id); 
    }
}
