package locadora;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

	// o ID do Cliente é o CPF

	protected List<Aluguel> alugueis = new ArrayList<Aluguel>(); // cliente pode ter vários aluguéis, mas aluguel só tem
																	// um cliente

	public Cliente() {
	}

	public Cliente(String nome, String CPF, String dataDeNascimento, int ddd, int numero) {
		super(nome, CPF, dataDeNascimento, ddd, numero);
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

}
