package locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO implements FilmeDAOInterface{

	Conexao c = new Conexao();

	@Override
	public int incrementoID() {
		int resultado = 0;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT id FROM filmes WHERE id = (SELECT MAX(id) FROM filmes)");
	
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
	public boolean inserir(Filme filme) {
		
		boolean resultado = false;
		
		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"INSERT INTO filmes (id, titulo, classificacao, genero, preco, aluguel_id) VALUES (?, ?, ?, ?, ?, ?);");
			
			preparedStatement.setInt(1, filme.getId());
			preparedStatement.setString(2, filme.getTitulo());
			preparedStatement.setString(3, filme.getClassificacao());
			preparedStatement.setString(4, filme.getGenero());
			preparedStatement.setDouble(5, filme.getPreco());
			preparedStatement.setInt(5, filme.getAluguelId());
			
			resultado = (preparedStatement.executeUpdate() == 1);
			
			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return resultado;
	}

	@Override
	public boolean atualizar(Filme filme) { // vai ter um List de Filmes e daí passa o List.get(i) - esse vai ser o filme
		// para atualizar eu preciso buscar e armazenar os resultados em uma lista
		
		boolean resultado = false;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"UPDATE filmes SET titulo = ?, classificacao = ?, genero = ?, preco = ? aluguel_id = ? WHERE id = ?");

			preparedStatement.setString(1, filme.getTitulo());
			preparedStatement.setString(2, filme.getClassificacao());
			preparedStatement.setString(3, filme.getGenero());
			preparedStatement.setDouble(4, filme.getPreco());
			preparedStatement.setInt(5, filme.getAluguelId());
			preparedStatement.setInt(6, filme.getId());

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	// preciso fazer uma busca antes pra poder excluir
	@Override
	public boolean excluir(int id) {
		boolean resultado = false;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement("DELETE FROM filmes WHERE id = ?");

			preparedStatement.setInt(1, id);

			resultado = (preparedStatement.executeUpdate() == 1);

			preparedStatement.close();
			Conexao.getConexao().close();
		} catch (SQLException ex) {
			return false;
		}

		return resultado;
	}

	// Essa busca é a que vai auxiliar na atualização. Eu procuro pelo nome, armazena em uma Lista, depois eu tenho todos
	// os dados do filme, daí é só passar por parâmetro
	@Override
	public List<Filme> getFilmeByNome(String titulo) {
		if (titulo == null || titulo.trim().length() == 0) {
			titulo = "%";
		} else {
			titulo = "%" + titulo + "%";
		}

		List<Filme> resultado = new ArrayList<Filme>();

		try {
			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT id, titulo, classificacao, genero, preco, aluguel_id FROM filmes WHERE upper(titulo) LIKE upper(?)");
			preparedStatement.setString(1, titulo);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Filme item = new Filme();
				item.setId(resultSet.getInt("id"));
				item.setTitulo(resultSet.getString("titulo"));
				item.setClassificacao(resultSet.getString("classificacao"));
				item.setGenero(resultSet.getString("genero"));
				item.setPreco(resultSet.getDouble("preco"));
				item.setAluguelId(resultSet.getInt("aluguel_id"));
				
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
	public Filme[] getFilmePorAluguelId(int AluguelId) {
		Filme[] resultado = new Filme[3];
		Filme item = null;
		
		int i = 0;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT id, titulo, classificacao, genero, preco, aluguel_id FROM filmes WHERE aluguel_id = ?");
			preparedStatement.setInt(1, AluguelId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				item = new Filme();
				
				item.setId(resultSet.getInt("id"));
				item.setTitulo(resultSet.getString("titulo"));
				item.setClassificacao(resultSet.getString("classificacao"));
				item.setGenero(resultSet.getString("genero"));
				item.setPreco(resultSet.getDouble("preco"));
				item.setAluguelId(resultSet.getInt("aluguel_id"));
				
				//resultado.add(item);
				
				resultado[i] = item;
				i++;
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
	public Filme getFilmePorId(int id) {
		Filme resultado = null;

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT id, titulo, classificacao, genero, preco, aluguel_id FROM filmes WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultado = new Filme();
				resultado.setId(resultSet.getInt("id"));
				resultado.setTitulo(resultSet.getString("titulo"));
				resultado.setClassificacao(resultSet.getString("classificacao"));
				resultado.setGenero(resultSet.getString("genero"));
				resultado.setPreco(resultSet.getDouble("preco"));
				resultado.setAluguelId(resultSet.getInt("aluguel_id"));
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
