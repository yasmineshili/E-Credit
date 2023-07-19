package Service;

import java.sql.SQLException;
import java.util.List;



public interface DemandeService {
    List<String> getAllUnites() throws SQLException; 
    int getNbreEcheancesByTypeCreditId(int typeCreditId) throws SQLException;
	List<String> getAllTypesCredit() throws SQLException;
	 int getTypeCreditIdByName(String typeName)   throws SQLException;
}

