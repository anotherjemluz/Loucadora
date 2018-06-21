package locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ClienteDAOInterface {

	Conexao c = new Conexao();

	@Override
	public boolean inserir(Cliente cliente) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("INSERT INTO cliente (nome, cpf, data_nascimento, ddd, numero) VALUES (?, ?, ?, ?, ?);");

			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCPF());
			preparedStatement.setString(3, cliente.getDataDeNascimento());
			preparedStatement.setInt(4, cliente.getDdd());
			preparedStatement.setInt(5, cliente.getNumero());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return resultado;
	}

	@Override
	public Cliente getClientesByCPF(String valor) {
		Cliente resultado = null;

		// aqui é pesquisa pelo valor = CPF

		try {

			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT nome, cpf, data_nascimento, ddd, numero FROM cliente WHERE cpf = ?");
			preparedStatement.setString(1, valor);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultado = new Cliente();
				resultado.setNome(resultSet.getString("nome"));
				resultado.setCPF(resultSet.getString("cpf"));
				resultado.setDataDeNascimento(resultSet.getString("data_nascimento"));
				resultado.setDdd(resultSet.getInt("ddd"));
				resultado.setNumero(resultSet.getInt("numero"));
			}

			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();

			AluguelDAO aluguel = new AluguelDAO();
			resultado.setAlugueis(aluguel.getAluguelByIdCliente(resultado.getCPF()));
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return resultado;
	}
	
	// busca pelo nome. As buscas estão em funções separadas por causa do tipo de retorno
	@Override
	public List<Cliente> getClientesByNome(String nome) {

		if (nome == null || nome.trim().length() == 0) {
			nome = "%";
		} else {
			nome = "%" + nome + "%";
		}

		List<Cliente> resultado = new ArrayList<Cliente>();
		Cliente item;
		AluguelDAO aluguel = new AluguelDAO();

		try {

			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT nome, cpf, data_nascimento FROM cliente WHERE upper(nome) LIKE upper(?)");
			preparedStatement.setString(1, nome);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				item = new Cliente();

				item.setNome(resultSet.getString("nome"));
				item.setCPF(resultSet.getString("cpf"));
				item.setDataDeNascimento(resultSet.getString("data_nascimento"));
				item.setDdd(resultSet.getInt("ddd"));
				item.setNumero(resultSet.getInt("numero"));
				item.setAlugueis(aluguel.getAluguelByIdCliente(item.getCPF()));

				resultado.add(item);

			}

			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return resultado;
	}

	// para atualizar precisa fazer a busca para ter a certeza de que é aquele
	// usuário que quer deletar
	@Override
	public boolean atualizar(Cliente cliente) {
		boolean resultado = false;

		try {

			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("UPDATE clientes SET nome = ?, data_nascimento = ?, ddd = ?, numero = ? WHERE cpf = ?");

			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getDataDeNascimento());
			preparedStatement.setInt(3, cliente.getDdd());
			preparedStatement.setInt(4, cliente.getNumero());
			preparedStatement.setString(5, cliente.getCPF());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	// para excluir eu preciso fazer uma busca antes para poder achar o Cliente e
	// perguntar se tem certeza que quer
	// deletar o cliente
	@Override
	public boolean excluir(String cpf) {
		boolean resultado = false;

		try {

			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement("DELETE FROM cliente WHERE cpf = ?");

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
