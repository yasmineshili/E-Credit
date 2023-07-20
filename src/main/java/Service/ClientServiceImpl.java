package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Entite.Client;

@ManagedBean
@ApplicationScoped
public class ClientServiceImpl implements ClientService {

	@Override
	public Client getClientByCIN(String cin) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Client client = null;

	    try {
	        connection = getConnection();

	        String sql = "SELECT * FROM client WHERE cin = ?";

	        statement = connection.prepareStatement(sql);
	        statement.setString(1, cin);

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            client = new Client();
	            client.setCin(resultSet.getString("cin"));
	            client.setNom(resultSet.getString("nom"));
	            client.setPrenom(resultSet.getString("prenom"));
	            client.setDateNaissance(resultSet.getDate("date_naissance").toLocalDate());
	            client.setSituationFamiliale(resultSet.getString("situation_familiale"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	      
	    }

	    return client;
	}

    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "Stage";
        String password = "Stage";
        return DriverManager.getConnection(url, username, password);
    }
}
