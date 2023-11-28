package seginf_av3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JavaDB.ConnectionFactory;
import dto.TriglicerideoDto;
import modelo.Paciente;
import modelo.Triglicerídeo;

public class ChaveSecretaDao {
    private Connection connection;

    public ChaveSecretaDao() throws Exception {
        this.connection = ConnectionFactory.getConnection();
    }

    public String recuperaSenhaSecreta(int chaveSecretaId) throws Exception {
        try {
            String query = "SELECT * FROM senha WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, chaveSecretaId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String chaveSecreta = resultSet.getString("chave_secreta");
                
                return chaveSecreta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
