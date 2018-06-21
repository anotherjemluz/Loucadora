package locadora;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {

	// login do funcionário é o CPF dele
	// ID do funcionário é o CPF

	private String senha;
	protected boolean gerente;
	protected List<Aluguel> alugueis = new ArrayList<>(); 
	// Um aluguel pode ser feito por um funcionário, mas um funcionário pode realizar vários aluguéis

	public Funcionario() {
	}

	public Funcionario(String nome, String CPF, String dataDeNascimento, boolean gerente, String senha, int ddd,
			int numero) {
		super(nome, CPF, dataDeNascimento, ddd, numero);
		this.gerente = gerente;
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

}
