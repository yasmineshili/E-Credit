package Entite;


import javax.persistence.*;


@Entity
public class Type_Credit {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)	
		  private int id;
		  private String nom_credit;
		  /*Example values : 0,06 , 0.08 ...*/
		  private float taux_credit;
		  
	
		    public Type_Credit(String nom_credit) {
			super();
			this.nom_credit = nom_credit;
		}

			public Type_Credit() {
			super();
		}

			public Type_Credit(int id, String nom_credit) {
			super();
			this.id = id;
			this.nom_credit = nom_credit;
		}

			public int getId() {
		        return id;
		    }

		    public void setId(int id) {
		        this.id = id;
		    }
		    
		    
		public String getNom_credit() {
			return nom_credit;
		}
		public void setNom_credit(String nom_credit) {
			this.nom_credit = nom_credit;
		}

		public float getTaux_credit() {
			return taux_credit;
		}

		public void setTaux_credit(float taux_credit) {
			this.taux_credit = taux_credit;
		}

}
