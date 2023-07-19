package Entite;


import javax.persistence.*;


	@Entity
	public class Unite {
		
		   @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)	
			  private int id;
			  private String nom_unite;
			  
			  
			  public Unite() {
					super();
					
				}
			  
			  
			    public Unite(String nom_unite) {
				super();
				this.nom_unite = nom_unite;
			}

				public Unite(int id, String nom_unite) {
				super();
				this.id = id;
				this.nom_unite = nom_unite;
			}

				public int getId() {
			        return id;
			    }

			    public void setId(int id) {
			        this.id = id;
			    }
			    
			    
			public String getNom_unite() {
				return nom_unite;
			}
			public void setNom_unite(String nom_unite) {
				this.nom_unite = nom_unite;
			}
			  
			  
		
		

}
