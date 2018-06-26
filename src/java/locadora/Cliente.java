package locadora;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

	// o ID do Cliente é o CPF

	protected List<Aluguel> alugueis = new ArrayList<Aluguel>(); // cliente pode ter vários aluguéis, mas aluguel só tem
																	// um cliente

	public Cliente() {
	}

	public Cliente(String nome, String cpf, String dataDeNascimento, int ddd, int numero) {
		super(nome, cpf, dataDeNascimento, ddd, numero);
		this.alugueis = null;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

}
