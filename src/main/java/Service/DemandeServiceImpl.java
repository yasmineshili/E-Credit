package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean
@ApplicationScoped
public class DemandeServiceImpl implements DemandeService {

 
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
        String username = "stage";
        String password = "stage";
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
