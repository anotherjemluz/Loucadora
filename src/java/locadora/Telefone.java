package locadora;

import java.io.Serializable;

public class Telefone implements Serializable {

	// Pessoa vai extender Telefone
	// chamada de polimorfismo será aqui pq telefone pra cliente é uma coisa, pra
	// funcionário é outra (são tabelmas diferentes)

	private int ddd;
	private int numero;

	public Telefone() {
	}

	public Telefone(int ddd, int numero) {
		this.ddd = ddd;
		this.numero = numero;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
