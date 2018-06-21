package locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements FuncionarioDAOInterface {

	Conexao c = new Conexao();

	@Override
	public boolean inserir(Funcionario funcionario) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"INSERT INTO funcionario (nome, cpf, data_nascimento, gerente, senha, ddd, numero) VALUES (?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, funcionario.getNome());
			preparedStatement.setString(2, funcionario.getCPF());
			preparedStatement.setString(3, funcionario.getDataDeNascimento());
			preparedStatement.setBoolean(4, funcionario.gerente);
			preparedStatement.setString(5, funcionario.getSenha());
			preparedStatement.setInt(6, funcionario.getDdd());
			preparedStatement.setInt(7, funcionario.getNumero());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	@Override
	public Funcionario getFuncionarioByCPF(String valor) {
		Funcionario resultado = null;

		// aqui é pesquisa pelo valor = CPF

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT nome, cpf, data_nascimento, gerente, senha, ddd, numero FROM funcionario WHERE cpf = ?");
			preparedStatement.setString(1, valor);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultado = new Funcionario();

				resultado.setNome(resultSet.getString("nome"));
				resultado.setCPF(resultSet.getString("cpf"));
				resultado.setDataDeNascimento(resultSet.getString("data_nascimento"));
				resultado.setGerente(resultSet.getBoolean("gerente"));
				resultado.setSenha(resultSet.getString("senha"));
				resultado.setDdd(resultSet.getInt("ddd"));
				resultado.setNumero(resultSet.getInt("numero"));
			}

			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return null;
		}

		return resultado;
	}

	// aqui é a busca pelo nome
	@Override
	public List<Funcionario> getFuncionarioByNome(String nome) {
		if (nome == null || nome.trim().length() == 0) {
			nome = "%";
		} else {
			nome = "%" + nome + "%";
		}

		List<Funcionario> resultado = new ArrayList<Funcionario>();
		AluguelDAO aluguel = new AluguelDAO();
		Funcionario item;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT nome, cpf, data_nascimento, gerente, senha, ddd, numero FROM funcionario WHERE upper(nome) LIKE upper(?)");
			preparedStatement.setString(1, nome);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				item = new Funcionario();

				item.setNome(resultSet.getString("nome"));
				item.setCPF(resultSet.getString("cpf"));
				item.setDataDeNascimento(resultSet.getString("data_nascimento"));
				item.setGerente(resultSet.getBoolean("gerente"));
				item.setSenha(resultSet.getString("senha"));
				item.setDdd(resultSet.getInt("ddd"));
				item.setNumero(resultSet.getInt("numero"));
				item.setAlugueis(aluguel.getAluguelByIdFuncionario(item.getCPF()));

				resultado.add(item);
			}

			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return null;
		}
		return resultado;
	}

	@Override
	public boolean atualizar(Funcionario funcionario) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"UPDATE funcionario SET nome = ?, data_nascimento = ?, gerente = ?, senha = ?, ddd = ?, numero = ? WHERE cpf = ?");

			preparedStatement.setString(1, funcionario.getNome());
			preparedStatement.setString(2, funcionario.getDataDeNascimento());
			preparedStatement.setBoolean(3, funcionario.gerente);
			preparedStatement.setString(4, funcionario.getSenha());
			preparedStatement.setInt(5, funcionario.getDdd());
			preparedStatement.setInt(6, funcionario.getNumero());
			preparedStatement.setString(7, funcionario.getCPF());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	@Override
	public boolean excluir(String cpf) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("DELETE FROM funcionario WHERE cpf = ?");

			preparedStatement.setString(1, cpf);

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

}
