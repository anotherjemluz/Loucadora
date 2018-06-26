package locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO implements FilmeDAOInterface, Incremento {

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
			preparedStatement.setString(2, filme.titulo);
			preparedStatement.setString(3, filme.classificacao);
			preparedStatement.setString(4, filme.genero);
			preparedStatement.setDouble(5, filme.preco);
			preparedStatement.setInt(6, filme.getAluguelId());
			
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

			preparedStatement.setString(1, filme.titulo);
			preparedStatement.setString(2, filme.classificacao);
			preparedStatement.setString(3, filme.genero);
			preparedStatement.setDouble(4, filme.preco);
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

	@Override
	public List<Filme> getFilmeByTitulo(String titulo)  {

		if (titulo == null || titulo.trim().length() == 0) {
			titulo = "%";
		} else {
			titulo = "%" + titulo + "%";
		}

		List<Filme> resultado = new ArrayList<Filme>();
		Filme item;
		//AluguelDAO aluguel = new AluguelDAO();

		try {

			if (Conexao.getConexao().isClosed()) {
				c = new Conexao();
			}

			PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(
					"SELECT id, titulo, classificacao, genero, preco, aluguel_id FROM filmes WHERE upper(titulo) LIKE upper(?)");
			preparedStatement.setString(1, titulo);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				item = new Filme();

				item.setId(resultSet.getInt("id"));
				item.titulo = (resultSet.getString("titulo"));
				item.classificacao = (resultSet.getString("classificacao"));
				item.genero = (resultSet.getString("genero"));
				item.preco = (resultSet.getDouble("preco"));
				item.setAluguelId(resultSet.getInt("aluguel_id"));

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
	
	@Override
	public Filme[] getFilmePorAluguelId(int AluguelId) {
		Filme[] resultado = new Filme[3];
		Filme item = new Filme();
		
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
				item.titulo = (resultSet.getString("titulo"));
				item.classificacao = (resultSet.getString("classificacao"));
				item.genero = (resultSet.getString("genero"));
				item.preco = (resultSet.getDouble("preco"));
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
		Filme resultado = new Filme();

		try {
			if(Conexao.getConexao().isClosed()) {c = new Conexao();}
			
			PreparedStatement preparedStatement = Conexao.getConexao()
					.prepareStatement("SELECT id, titulo, classificacao, genero, preco, aluguel_id FROM filmes WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultado = new Filme();
				
				resultado.setId(resultSet.getInt("id"));
				resultado.titulo = (resultSet.getString("titulo"));
				resultado.classificacao = (resultSet.getString("classificacao"));
				resultado.genero = (resultSet.getString("genero"));
				resultado.preco = (resultSet.getDouble("preco"));
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
