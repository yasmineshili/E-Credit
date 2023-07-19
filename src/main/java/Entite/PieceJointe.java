package Entite;


import javax.persistence.*;


@Entity
@Table(name = "piecejointe")
public class PieceJointe  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document")
    private Document document;

    @Column(name = "obligatoire")
    private String obligatoire;

    @Column(name = "statut")
    private String statut;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande")
    private DemandeCredit demandeCredit;
    
    
    

	public PieceJointe() {
		super();
	}



	public PieceJointe(Document document, String obligatoire, String statut, DemandeCredit demandeCredit) {
		super();
		this.document = document;
		this.obligatoire = obligatoire;
		this.statut = statut;
		this.demandeCredit = demandeCredit;
	}



	public PieceJointe(Long id, Document document, String obligatoire, String statut, DemandeCredit demandeCredit) {
		super();
		this.id = id;
		this.document = document;
		this.obligatoire = obligatoire;
		this.statut = statut;
		this.demandeCredit = demandeCredit;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getObligatoire() {
		return obligatoire;
	}

	public void setObligatoire(String obligatoire) {
		this.obligatoire = obligatoire;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}



	public DemandeCredit getDemandeCredit() {
		return demandeCredit;
	}



	public void setDemandeCredit(DemandeCredit demandeCredit) {
		this.demandeCredit = demandeCredit;
	}
    
    
    
    
    
    
    
    

}
