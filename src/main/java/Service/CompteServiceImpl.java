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

import Entite.Compte;


@ManagedBean
@ApplicationScoped
public class CompteServiceImpl implements CompteService {

	@Override
	public List<String> getAllNumeroComptesForClient(String cin) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List<String> numerosComptes = new ArrayList<>();

	    try {
	        connection = getConnection();

	        String sql = "SELECT tc.numero_compte FROM type_compte tc " +
	                "JOIN compte c ON tc.id = c.id_type_compte " +
	                "JOIN client cl ON c.id_client = cl.id " +
	                "WHERE cl.cin = ?";



	        statement = connection.prepareStatement(sql);
	        statement.setString(1, cin);

	        resultSet = statement.executeQuery();

	        System.out.println("Nombre de lignes récupérées : " + numerosComptes.size());

	        while (resultSet.next()) {
	            String numeroCompte = resultSet.getString("numero_compte");
	            System.out.println("Numéro de compte : " + numeroCompte);
	            numerosComptes.add(numeroCompte);
	        }

	        System.out.println("Nombre de lignes après la boucle : " + numerosComptes.size());

	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(connection, statement, resultSet);
	    }

	    return numerosComptes;
	}
	
	@Override
	public Compte getCompteByNumero(String numero) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Compte compte = null;

	    try {
	        connection = getConnection();

	        String sql = "SELECT c.date_ouverture, c.devise, tc.numero_compte " +
	                     "FROM compte c " +
	                     "JOIN type_compte tc ON c.id_type_compte = tc.id " +
	                     "WHERE tc.numero_compte = ?";

	        statement = connection.prepareStatement(sql);
	        statement.setString(1, numero);

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            compte = new Compte();
	            compte.setDateOuverture(resultSet.getDate("date_ouverture").toLocalDate());
	            compte.setDevise(resultSet.getString("devise"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(connection, statement, resultSet);
	    }

	    return compte;
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
