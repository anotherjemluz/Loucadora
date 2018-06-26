package locadora;

import java.util.ArrayList;
import java.util.List;

public class Locadora {

	public static void main(String[] args) {
	
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		List<Filme> filmes = new ArrayList<Filme>();
		
		FilmeDAO f2 = new FilmeDAO();
		
		ClienteDAO c = new ClienteDAO();
		
		filmes = f2.getFilmeByTitulo("CURTINDO A VIDA ADOIDADO");
		
		System.out.println(filmes.get(0).classificacao);
		
		
		/*	
		Filme f = new Filme(f2.incrementoID(), "CURTINDO A VIDA ADOIDADO", "livre", "drama", 3.5);
		
		f2.inserir(f);
		
		AluguelDAO a = new AluguelDAO();
		
		AluguelDAO a2 = new AluguelDAO();
		
		Aluguel al = new Aluguel(a.incrementoID(), "14/06/2018", "17/06/2018", "151.302.625.30", "000.000.000-95");
		a.inserir(al);
		
		Aluguel a3 = new Aluguel(a2.incrementoID(), "16/06/2018", "19/06/2018", "151.302.625.30", "051.864.303-40");
		a.inserir(a3);
		
		
		
		clientes = c.getClientesByNome("Rosilene");
		
		a.getAluguelByIdCliente("025.241.578-95");
		
		Cliente c1 = new Cliente("Kelma", "123.000.000-95", "03/04/1979", 85, 32843279);
		 
		Cliente c2 = new Cliente("Marta", "123.864.000-40", "15/06/1985", 88, 988755793);
		 
		c.inserir(c1);
		
		c.inserir(c2);
		*/
		//clientes = c.getClientesByNome("Rosilene");
		//Funcionario f1 = new Funcionario("Kelma", "123.000.000-95", "03/04/1979", true, "123", 85, 32843279);
		
		//Cliente c1 = new Cliente("Marcela", "123.123.000-00", "03/04/1979", 85, 32843279);
		//c.inserir(c1);
		
		Cliente c3 = new Cliente();
		
		c3 = c.getClientesByCPF("009.000.321-00");
		
		
		
		
		
		//System.out.println(c3.getDdd());
		/*
		if(c3.getNome() == null) {
			System.out.println("nao foi possivel encontrar");
		} else {
			System.out.println(c3.getNome());
		}
		
		*/
		//Funcionario f4 = new Funcionario();
		
		//Funcionario f1 = new Funcionario("Rafaela", "000.111.222-34", "03/04/1979", true, "123", 85, 32843279);

		//FuncionarioDAO fu = new FuncionarioDAO();
		
		//fu.inserir(f1);
		
		//f4 = fu.getFuncionarioByCPF("Kelma");
		
		//fu.inserir(f1);

		//System.out.println(clientes.get(0).getCPF());
		
		//System.out.println(f4.getNome());
		
		//filmes = f2.getFilmeByTitulo("CURTINDO A VIDA ADOIDADO");
		
		//System.out.println(filmes.get(0).genero);
		
		

	}

}
