package locadora;

public class Pessoa extends Telefone {

	private String cpf;
	private String nome;
	private String dataDeNascimento;

	public Pessoa() {
	}

	public Pessoa(String nome, String cpf, String dataDeNascimento, int ddd, int numero) {
		super(ddd, numero);
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String CPF) {
		this.cpf = CPF;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

}
