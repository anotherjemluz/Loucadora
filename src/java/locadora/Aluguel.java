package locadora;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluguel implements Serializable {

	private int id;
	protected String dataDeLocacao;
	protected String dataDeDevolucao;
	private String idFuncionario;
	private String idCliente;
	// private List<Filme> filmes = new ArrayList<>(); // um aluguel tem vários
	// filmes, mas um filme são tem um aluguel
	private Filme[] filmes = new Filme[3];
	protected boolean filmeDevolvido;

	public Aluguel() {
	}

	public Aluguel(int id, String dataDeLocacao, String dataDeDevolucao, String idFuncionario, String idCliente) {
		this.id = id;
		this.dataDeLocacao = dataDeLocacao;
		this.dataDeDevolucao = dataDeDevolucao;
		this.idFuncionario = idFuncionario;
		this.idCliente = idCliente;
		filmeDevolvido = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDataDeLocacao(String dataDeLocacao) {
		this.dataDeLocacao = dataDeLocacao;
	}

	public void setDataDeDevolucao(String dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public void setFilmeDevolvido(boolean filmeDevolvido) {
		this.filmeDevolvido = filmeDevolvido;
	}

	public Filme[] getFilmes() {
		return filmes;
	}

	public void setFilmes(Filme[] filmes) {
		this.filmes = filmes;
	}

}
