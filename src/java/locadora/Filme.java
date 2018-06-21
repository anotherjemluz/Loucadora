package locadora;

import java.io.Serializable;
import java.util.ArrayList;

public class Filme implements Serializable {

	// só é possível alugar 3 filmes por cliente
	
	private int id;
	private String titulo;
	private String genero;
	private String classificacao;
	private double preco;
	private int aluguelId; // chave estrangeira para id do aluguel
	
	public Filme() {}

	public Filme(int id, String titulo, String classificacao, String genero, double preco) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.classificacao = classificacao;
		this.preco = preco;
		aluguelId = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getAluguelId() {
		return aluguelId;
	}

	public void setAluguelId(int aluguelId) {
		this.aluguelId = aluguelId;
	}

}
