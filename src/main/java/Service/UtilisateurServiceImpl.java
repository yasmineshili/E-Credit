package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;

import Entite.Role;
import javax.faces.bean.ManagedBean;


@ManagedBean
@ApplicationScoped
public class UtilisateurServiceImpl implements UtilisateurService {

    @Override
    public String login(String email, String mot_de_passe) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean loggedIn = false;
        Role role = null;

        try {
            connection = getConnection();

            String sql = "SELECT u.*, r.id AS role_id, r.nom AS role_nom FROM utilisateur u INNER JOIN Role r ON u.role_id = r.id WHERE u.email = ? AND u.mot_de_passe = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, mot_de_passe);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                loggedIn = true;
                int roleId = resultSet.getInt("role_id");
                String roleNom = resultSet.getString("role_nom");
                role = new Role(roleId, roleNom);

                if (role.getId() == 1) { 
                    return "Admin.xhtml?faces-redirect=true";
                } else if (role.getId() == 2) { 
                    return "Demande.xhtml?faces-redirect=true";
                }
             
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           
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

        return "Login.xhtml?faces-redirect=true";
    }
    
    @Override
    public String logout() {
      
    	javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Login.xhtml?faces-redirect=true";
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String username = "Stage"; 
        String password = "Stage"; 
        return DriverManager.getConnection(url, username, password);
    }
}
