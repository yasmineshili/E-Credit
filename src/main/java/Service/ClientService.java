package Service;

import java.sql.SQLException;

import Entite.Client;

public interface ClientService {
    Client getClientByCIN(String cin) throws SQLException;
}
