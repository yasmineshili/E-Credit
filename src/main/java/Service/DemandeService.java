package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Entite.DemandeCredit;



public interface DemandeService {
    List<String> getAllUnites() throws SQLException; 
    int getNbreEcheancesByTypeCreditId(int typeCreditId) throws SQLException;
	List<String> getAllTypesCredit() throws SQLException;
	 int getTypeCreditIdByName(String typeName)   throws SQLException;
	 public List<DemandeCredit> getAllDemandedServices() throws SQLException;
	 public DemandeCredit serializeDemande(ResultSet resultSet);
	 public void accepterDemande(int idDemande) ;
	 public void rejeterDemande(int idDemande);
	 DemandeCredit getDemande(int id);
}

