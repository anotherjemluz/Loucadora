package locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Locadora";
	private static final String JDBC_USER = "postgres";
	private static final String JDBC_PASSWORD = "romoufer_+";

	private static Connection connection;

	public Conexao() {
		try {

			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			System.out.println("Deu certo a conexão com o banco");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConexao() {
		return connection;
	}

}
