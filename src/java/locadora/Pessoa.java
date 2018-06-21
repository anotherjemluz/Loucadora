package locadora;

import java.io.Serializable;

public class Pessoa extends Telefone implements Serializable {

	private String nome;
	private String CPF;
	private String dataDeNascimento;

	public Pessoa() {
	}

	public Pessoa(String nome, String CPF, String dataDeNascimento, int ddd, int numero) {
		super(ddd, numero);
		this.nome = nome;
		this.CPF = CPF;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

}
