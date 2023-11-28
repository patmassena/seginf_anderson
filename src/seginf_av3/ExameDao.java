package seginf_av3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JavaDB.ConnectionFactory;
import dto.TriglicerideoDto;
import modelo.Paciente;
import modelo.Triglicerídeo;

public class ExameDao {
    private DeCrypt deCrypt;
    private Connection connection;

    public ExameDao(DeCrypt deCrypt) throws Exception {
        this.deCrypt = deCrypt;
        this.connection = ConnectionFactory.getConnection();
    }

    public void insereTriglicerídeo(TriglicerideoDto triglicerideoDto, Paciente p) throws Exception {
        try {
            String query = "INSERT INTO triglicerideos (tipo_metrica, resultado, paciente_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setBytes(1, deCrypt.encrypt(triglicerideoDto.getTipoMetrica()));
            preparedStatement.setBytes(2, deCrypt.encrypt(triglicerideoDto.getResultado()));
            preparedStatement.setInt(3, p.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TriglicerideoDto recuperaExame(int exameId) throws Exception {
        try {
            String query = "SELECT * FROM triglicerideos WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, exameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String tipoMetrica = deCrypt.decrypt(resultSet.getBytes("tipo_metrica"));
                String resultado = deCrypt.decrypt(resultSet.getBytes("resultado"));
                
                TriglicerideoDto triglicerideoDto = new TriglicerideoDto(tipoMetrica, resultado);
                return triglicerideoDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
