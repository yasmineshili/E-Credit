package Entite;

import javax.persistence.*;

@Entity
public class Type_Compte {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	  private Long id;
	    private String numero;
		
	    public Type_Compte(Long id, String numero) {
			super();
			this.id = id;
			this.numero = numero;
		}

		public Type_Compte() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}
	    
	    


}
