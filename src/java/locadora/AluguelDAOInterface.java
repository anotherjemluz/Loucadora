package locadora;

import java.util.List;

public interface AluguelDAOInterface {

	//int incrementoID();

	boolean inserir(Aluguel aluguel);

	boolean atualizar(Aluguel aluguel);

	boolean excluir(int id);

	List<Aluguel> getAluguelByIdCliente(String idCliente);

	List<Aluguel> getAluguelByIdFuncionario(String idFuncionario);
	
}
