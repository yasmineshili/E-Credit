package Service;

import java.sql.SQLException;
import java.util.List;

import Entite.Compte;


public interface CompteService {
    List<String> getAllNumeroComptesForClient(String cin) throws SQLException;

	Compte getCompteByNumero(String numero) throws SQLException;
}
