package Entite;


import javax.persistence.*;

@Entity
	@Table(name = "document")
	public class Document  {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;

	    @Column(name = "nom_document")
	    private String nomDocument;
	    
	    
	  
	    

		public Document(String nomDocument) {
			super();
			this.nomDocument = nomDocument;
		}

		public Document() {
			super();
		}

		public Document(Long id, String nomDocument) {
			super();
			this.id = id;
			this.nomDocument = nomDocument;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNomDocument() {
			return nomDocument;
		}

		public void setNomDocument(String nomDocument) {
			this.nomDocument = nomDocument;
		}
	
	

	

}
