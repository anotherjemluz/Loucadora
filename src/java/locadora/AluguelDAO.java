package locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AluguelDAO implements AluguelDAOInterface, Incremento {

	Conexao c = new Conexao();

	@Override
	public int incrementoID() {
		int resultado = 0;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT id FROM aluguel WHERE id = (SELECT MAX(id) FROM aluguel)");
	
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultado = resultSet.getInt("id") + 1;
				System.out.println(resultado);
			}
		
			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();
					
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return resultado;
	}

	@Override
	public boolean inserir(Aluguel aluguel) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"INSERT INTO aluguel (id, data_locacao, data_devolucao, id_funcionario, id_cliente, filme_devolvido) VALUES (?, ?, ?, ?, ?, ?);");

			preparedStatement.setInt(1, aluguel.getId());
			preparedStatement.setString(2, aluguel.dataDeLocacao);
			preparedStatement.setString(3, aluguel.dataDeDevolucao);
			preparedStatement.setString(4, aluguel.getIdFuncionario());
			preparedStatement.setString(5, aluguel.getIdCliente());
			preparedStatement.setBoolean(6, aluguel.filmeDevolvido);

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();

			// resultado = true;
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return resultado;
	}

	// precisa buscar para poder atualizar (buscar aluguel pelo nome ou cpf do
	// cliente)
	@Override
	public boolean atualizar(Aluguel aluguel) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"UPDATE aluguel SET data_locacao = ?, data_devolucao = ?, id_funcionario = ?, id_cliente = ?, filme_devolvido = ? WHERE id = ?");

			preparedStatement.setString(1, aluguel.dataDeLocacao);
			preparedStatement.setString(2, aluguel.dataDeDevolucao);
			preparedStatement.setString(3, aluguel.getIdFuncionario());
			preparedStatement.setString(4, aluguel.getIdCliente());
			preparedStatement.setBoolean(5, aluguel.filmeDevolvido);
			preparedStatement.setInt(6, aluguel.getId());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	// precisa buscar para poder excluir (buscar aluguel pelo nome ou cpf do
	// cliente)
	@Override
	public boolean excluir(int id) {
		boolean resultado = false;

		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement("DELETE FROM aluguel WHERE id = ?");

			preparedStatement.setInt(1, id);

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	// o ID do cliente é o CPF
	@Override
	public List<Aluguel> getAluguelByIdCliente(String idCliente) {

		List<Aluguel> resultado = new ArrayList<Aluguel>();
	
		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}
			
			Aluguel item;

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT id, data_locacao, data_devolucao, id_funcionario, id_cliente, filme_devolvido FROM aluguel WHERE id_cliente = ?");
			preparedStatement.setString(1, idCliente);

			ResultSet resultSet = preparedStatement.executeQuery();

			
			while (resultSet.next()) {

				item = new Aluguel();

				item.setId(resultSet.getInt("id"));
				item.setDataDeLocacao(resultSet.getString("data_locacao"));
				item.setDataDeDevolucao(resultSet.getString("data_devolucao"));
				item.setIdFuncionario(resultSet.getString("id_funcionario"));
				item.setIdCliente(resultSet.getString("id_cliente"));
				item.setFilmeDevolvido(resultSet.getBoolean("filme_devolvido"));

				resultado.add(item);

			}
	
			resultSet.close();
			preparedStatement.close();
			Conexao.getConexao().close();
			
			//return resultado;
		} catch (SQLException ex) {
			return null;
		}
	

		return resultado;
	}

	// o ID do Funcionário é o CPF
	@Override
	public List<Aluguel> getAluguelByIdFuncionario(String idFuncionario) {

		List<Aluguel> resultado = new ArrayList<Aluguel>();
		FilmeDAO filmes = new FilmeDAO();
		Aluguel item;
		
		try {
			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT id, data_locacao, data_devolucao, id_funcionario, id_cliente, filme_devolvido FROM aluguel WHERE id_funcionario = ?");
			preparedStatement.setString(1, idFuncionario);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				item = new Aluguel();

				item.setId(resultSet.getInt("id"));
				item.setDataDeLocacao(resultSet.getString("data_locacao"));
				item.setDataDeDevolucao(resultSet.getString("data_devolucao"));
				item.setIdFuncionario(resultSet.getString("id_funcionario"));
				item.setIdCliente(resultSet.getString("id_cliente"));
				item.setFilmeDevolvido(resultSet.getBoolean("filme_devolvido"));
				item.setFilmes(filmes.getFilmePorAluguelId(item.getId()));
				
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

}
