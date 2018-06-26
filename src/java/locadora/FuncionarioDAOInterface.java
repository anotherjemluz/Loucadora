package locadora;

import java.util.List;

public interface FuncionarioDAOInterface {

	boolean inserir(Funcionario funcionario);

	Funcionario getFuncionarioByCPF(String valor);

	List<Funcionario> getFuncionarioByNome(String nome);

	boolean atualizar(Funcionario funcionario);

	boolean excluir(String cpf);

}
