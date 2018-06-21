package locadora;

import java.util.List;

public interface ClienteDAOInterface {

	boolean inserir(Cliente cliente);

	Cliente getClientesByCPF(String valor);

	List<Cliente> getClientesByNome(String nome);

	boolean atualizar(Cliente cliente);

	boolean excluir(String cpf);

}
