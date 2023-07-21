package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entite.Client;
import Entite.Compte;
import Entite.DemandeCredit;
import Entite.Type_Credit;
import Entite.Unite;


@ManagedBean
@ApplicationScoped
public class DemandeServiceImpl implements DemandeService {

	@Override
	public void accepterDemande(int idDemande) {
		Connection connection = null;
		Statement  statement = null;

		try {
	        connection = getConnection();
		String sql = "UPDATE DEMANDECREDIT " +
	            "SET ETAT = 'Rejete' WHERE id ="+String.valueOf(idDemande);
	    statement = connection.createStatement();
	    statement.executeUpdate(sql);
     
		} catch (SQLException e) {
	        e.printStackTrace();
	    } 
		
	}
	@Override
public void rejeterDemande(int idDemande) {
		Connection connection = null;
		Statement  statement = null;

		try {
	        connection = getConnection();
		String sql = "UPDATE DEMANDECREDIT " +
	            "SET ETAT = 'Valide' WHERE id ="+String.valueOf(idDemande);
	    statement = connection.createStatement();
	    statement.executeUpdate(sql);
     
		} catch (SQLException e) {
	        e.printStackTrace();
	    } 
		
	
	}
	@Override
	public DemandeCredit getDemande(int id) {
		List<DemandeCredit> demandes = getAllDemandedServices();
	    DemandeCredit demande = new DemandeCredit();
	    for (DemandeCredit d : demandes) {
	        if (d.getId() == id) {
	        	demande=d;
	        }
	    }
	    return demande;
		
	}
	  public List<DemandeCredit> getAllDemandedServices() {
	        List<DemandeCredit> demandes = new ArrayList<>();

	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = getConnection();

	            String sql = "SELECT d.id,cl.NOM, d.montant, d.NBRE_ECHEANCE,c.id_client,d.ID_COMPTE,c.ID_CLIENT,d.ID_TYPE_CREDIT,d.ID_UNITE,u.NOM_UNITE,tc.NOM_CREDIT,c.DATE_OUVERTURE,c.DEVISE,cl.CIN,cl.DATE_NAISSANCE,cl.NOM,cl.PRENOM,cl.SITUATION_FAMILIALE,d.DATECREDIT,d.ETAT FROM DEMANDECREDIT d "
	            		+
	                    "JOIN COMPTE c ON d.ID_COMPTE = c.ID "
			            +
		                "JOIN CLIENT cl ON c.ID_CLIENT = cl.ID "
			            +
			            "JOIN TYPE_CREDIT tc ON d.ID_TYPE_CREDIT = tc.ID "
			            +
			            "JOIN UNITE u ON d.ID_UNITE = u.ID";
	            statement = connection.prepareStatement(sql);
	            resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	/*TODO make it to a separate function*/
	            	 DemandeCredit demande = new DemandeCredit();
	            	demande = serializeDemande(resultSet);
	            	demandes.add(demande);
	                
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources(connection, statement, resultSet);
	        }

	        return demandes;
	    }

	@Override
	public DemandeCredit serializeDemande(ResultSet resultSet) {
		 /*Serialize Demande*/
		
        DemandeCredit demande = new DemandeCredit();
        try {
			demande.setMontant(resultSet.getInt("montant"));
		
        demande.setNbreEcheance(resultSet.getInt("NBRE_ECHEANCE"));
        demande.setId(resultSet.getInt("id"));
       demande.setDateCredit(resultSet.getDate("DATECREDIT").toLocalDate());
       demande.setEtat(resultSet.getString("ETAT"));
        /*-------------*/
        
        /*Serialize Compte*/
        Compte compte = new Compte();
        compte.setDateOuverture(resultSet.getDate("DATE_OUVERTURE").toLocalDate());
        compte.setDevise(resultSet.getString("DEVISE"));
        compte.setId(resultSet.getInt("ID_COMPTE"));
        
        /*-------------*/
        
        /*Serialize Client*/
        Client client = new Client();
        client.setCin(resultSet.getString("CIN"));
        client.setDateNaissance(resultSet.getDate("DATE_OUVERTURE").toLocalDate());
        client.setId(resultSet.getLong("ID_CLIENT"));
        client.setNom(resultSet.getString("NOM"));
        client.setPrenom(resultSet.getString("PRENOM"));
        client.setSituationFamiliale(resultSet.getString("SITUATION_FAMILIALE"));
        compte.setClient(client);
        demande.setCompte(compte);
        /*-------------*/
        
        /*Serialize TypeCredit*/
        Type_Credit typecredit = new Type_Credit();
        typecredit.setId(resultSet.getInt("ID_TYPE_CREDIT"));
        typecredit.setNom_credit(resultSet.getString("NOM_CREDIT"));
        demande.setTypeCredit(typecredit);
        /*-------------*/
        
        /*Serialize Unite*/
        Unite unite = new Unite();
        unite.setId(resultSet.getInt("ID_UNITE"));
        unite.setNom_unite(resultSet.getString("NOM_UNITE"));
        demande.setUnite(unite);
        /*-------------*/
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return demande;
	}
	
 
	@Override
	public List<String> getAllTypesCredit() throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List<String> typesCredit = new ArrayList<>();

	    try {
	        connection = getConnection();

	        String sql = "SELECT nom_credit FROM type_credit";

	        statement = connection.prepareStatement(sql);

	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String typeCredit = resultSet.getString("nom_credit");
	            typesCredit.add(typeCredit);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(connection, statement, resultSet);
	    }

	    return typesCredit;
	}



	@Override
	public List<String> getAllUnites() throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List<String> unites = new ArrayList<>();

	    try {
	        connection = getConnection();

	        String sql = "SELECT nom_unite FROM unite";

	        statement = connection.prepareStatement(sql);

	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String unite = resultSet.getString("nom_unite");
	            unites.add(unite);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(connection, statement, resultSet);
	    }

	    return unites;
	}
	
	@Override
	public int getNbreEcheancesByTypeCreditId(int typeCreditId) throws SQLException {
	    int nbreEcheances = 0;

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT nbre_echeance FROM demandecredit WHERE id_type_credit = ?");
	    ) {
	        statement.setInt(1, typeCreditId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                nbreEcheances = resultSet.getInt("nbre_echeance");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nbreEcheances;
	}

	@Override
	public int getTypeCreditIdByName(String typeName) throws SQLException {
	    int typeCreditId = 0;

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT id FROM type_credit WHERE nom_credit = ?");
	    ) {
	        statement.setString(1, typeName);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                typeCreditId = resultSet.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return typeCreditId;
	}


        


    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "Stage";
        String password = "Stage";
        return DriverManager.getConnection(url, username, password);
    }
    
    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
