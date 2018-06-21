package locadora;

import java.util.List;

public interface FilmeDAOInterface {

	int incrementoID();

	boolean inserir(Filme filme);

	boolean atualizar(Filme filme);

	boolean excluir(int id);

	List<Filme> getFilmeByNome(String titulo);

	Filme[] getFilmePorAluguelId(int AluguelId);

	Filme getFilmePorId(int id);

}
